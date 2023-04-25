package com.dev_akash.assignmentlistedapp.ui.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.dev_akash.assignmentlistedapp.R
import com.dev_akash.assignmentlistedapp.databinding.ActivityMainBinding
import com.dev_akash.assignmentlistedapp.ui.adapter.LinksTabAdapter
import com.dev_akash.assignmentlistedapp.ui.adapter.StatsAdapter
import com.dev_akash.assignmentlistedapp.utils.Constants.FAQ_WEBSITE_LINK
import com.dev_akash.assignmentlistedapp.utils.Constants.SUPPORT_WHATSAPP_NUMBER
import com.dev_akash.assignmentlistedapp.utils.DateTimeUtils.getGreetingText
import com.dev_akash.assignmentlistedapp.utils.SharedPrefs
import com.dev_akash.assignmentlistedapp.viewmodel.MainViewModel
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val statsAdapter = StatsAdapter()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel.getDashboardData()

        setUpTabs()

        setUserProfile()

        setCharts()

        setStats()

        setUpButtons()

    }

    private fun setCharts() {
        configureLineChart()
        configureXAxis()
        configureYAxis()
        val lineChart = binding.chartLayout.chart

        viewModel.chartsLiveData.observe(this) {
            it?.let { entries ->
                val dataSet = LineDataSet(entries, "Data")
                dataSet.setDrawCircles(false)
                dataSet.setDrawValues(false)
                dataSet.lineWidth = 2f
                dataSet.color = ContextCompat.getColor(this, R.color.app_primary)

                val gradientColors = intArrayOf(
                    ContextCompat.getColor(this, R.color.gradient_start),
                    ContextCompat.getColor(this, R.color.gradient_end)
                )
                val gradientDrawable = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, gradientColors)
                dataSet.fillDrawable = gradientDrawable
                dataSet.setDrawFilled(true)

                val lineData = LineData(dataSet)
                lineChart.data = lineData
                lineChart.animateXY(1000,1000)
                lineChart.invalidate()
            }
        }


    }

    private fun configureLineChart() {
        binding.chartLayout.chart.apply {
            description.isEnabled = false
            legend.isEnabled = false
            axisRight.isEnabled = false
            isDragEnabled = false
            setScaleEnabled(false)

            setDrawGridBackground(false)
            setDrawBorders(false)
        }
    }

    private fun configureYAxis() {
        binding.chartLayout.chart.axisLeft.apply {
            setDrawAxisLine(false)
            setDrawGridLines(true)
            gridColor = getColor(R.color.grid_line_color)
            axisMinimum = 0f
            axisMaximum = 100f
            textColor = ContextCompat.getColor(this@MainActivity, R.color.label_color)
            textSize = 9f
            setLabelCount(5, true)
        }
    }

    private fun configureXAxis() {
        binding.chartLayout.chart.xAxis.apply {
            setDrawAxisLine(false)
            setDrawGridLines(true)
            gridColor = getColor(R.color.grid_line_color)
            valueFormatter = IndexAxisValueFormatter(
                arrayOf("", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
            )
            setLabelCount(13,true)
            textColor = ContextCompat.getColor(this@MainActivity, R.color.label_color)
            textSize = 9f
            position = XAxis.XAxisPosition.BOTTOM

        }
    }

    private fun setUpButtons() {
        binding.btnChatWithUs.setOnClickListener {
            val mobileNumber = SharedPrefs.getStringParam(SUPPORT_WHATSAPP_NUMBER)
            if (isWhatsAppInstalled()) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("http://api.whatsapp.com/send?phone=+91$mobileNumber")
                startActivity(intent)
            } else {
               showToast(getString(R.string.whatsapp_error_msg))
            }
        }

        binding.btnFaq.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).also { it.data = Uri.parse(FAQ_WEBSITE_LINK) }
            startActivity(intent)
        }
    }

    private fun setUserProfile() {
        binding.apply {
            tvGreeting.text = getGreetingText()
            tvName.text = "Ajay Manva" //Hardcoding name as API doesn't provide this
        }
    }

    private fun setUpTabs() {
        val tabAdapter = LinksTabAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.isSaveEnabled = true
        binding.viewPager.adapter = tabAdapter

        binding.tabLayout.apply {
            addTab(newTab().setText(getString(R.string.top_links)))
            addTab(newTab().setText(getString(R.string.recent_links)))
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPager.currentItem = tab?.position ?: 0
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })

        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tabLayout.apply {
                    selectTab(getTabAt(position))
                }
            }
        })
    }

    private fun setStats() {
        binding.rvStats.apply {
            adapter = statsAdapter
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.HORIZONTAL,false
            )
        }

        viewModel.statsLiveData.observe(this) {
            statsAdapter.submitList(it)
        }
    }

    private fun isWhatsAppInstalled(): Boolean {
        val isInstalled: Boolean = try {
            packageManager.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
        return isInstalled
    }

    private fun showToast(msg:String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}