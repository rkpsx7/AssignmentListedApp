package com.dev_akash.assignmentlistedapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.dev_akash.assignmentlistedapp.databinding.ActivityMainBinding
import com.dev_akash.assignmentlistedapp.utils.DateTimeUtils.getMonthValueFromDate
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val map: HashMap<String, Int> = hashMapOf(
        "2023-03-25" to 0,
        "2023-03-26" to 0,
        "2023-03-27" to 0,
        "2023-03-28" to 0,
        "2023-03-29" to 0,
        "2023-03-30" to 0,
        "2023-03-31" to 0,
        "2023-04-01" to 0,
        "2023-04-02" to 0,
        "2023-04-03" to 0,
        "2023-04-04" to 0,
        "2023-04-05" to 2,
        "2023-04-06" to 0,
        "2023-04-07" to 0,
        "2023-04-08" to 0,
        "2023-04-09" to 10,
        "2023-04-10" to 2,
        "2023-04-11" to 0,
        "2023-04-12" to 0,
        "2023-04-13" to 0,
        "2023-04-14" to 0,
        "2023-04-15" to 0,
        "2023-04-16" to 0,
        "2023-04-17" to 0,
        "2023-04-18" to 0,
        "2023-04-19" to 0,
        "2023-04-20" to 0,
        "2023-04-21" to 0,
        "2023-04-22" to 0,
        "2023-04-23" to 0,
        "2023-04-24" to 0
    )

    val labels = mutableListOf<String>()
    val datas = mutableListOf<Entry>()


    private val viewModel: MainViewModel by viewModels()

    private val statsAdapter = StatsAdapter()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel.getDashboardData()

        setUpTabs()

        setStats()


        val xtr = map.map {
            Entry(getMonthValueFromDate(it.key), it.value.toFloat())
        }

        binding.chartLayout.chart.apply {

            val dataSet = LineDataSet(xtr, "Label1")
            dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
            dataSet.color = Color.BLUE
            dataSet.valueTextColor = Color.GREEN

            data = LineData(dataSet)
            this.invalidate()
        }

        binding.chartLayout.chart.xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            setDrawAxisLine(true)
            setDrawGridLines(false)
            setDrawLabels(true)
            labelCount = labels.size // important
            spaceMax = 0.5f // optional
            spaceMin = 0.5f // optional

//            valueFormatter = object : ValueFormatter() {
//                override fun getFormattedValue(value: Float): String {
//                    return labels[value.toInt()]
//                }
//            }
        }

        binding.chartLayout.chart.getAxis(YAxis.AxisDependency.LEFT).apply {
            spaceMax = 0.5f // optional
            spaceMin = 0.5f // optional
            labelCount = 5

            axisMaximum = 100f
        }

    }

    private fun setUpTabs() {
        val tabAdapter = LinksTabAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.isSaveEnabled = true
        binding.viewPager.adapter = tabAdapter

//        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
//            tab.text = getTabNames(position)
//        }.attach()

        binding.tabLayout.apply {
            addTab(newTab().setText("Top Links"))
            addTab(newTab().setText("Recent Links"))

        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPager.currentItem = tab?.position ?: 0
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

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
            layoutManager = LinearLayoutManager(this@MainActivity).also {
                it.orientation = LinearLayoutManager.HORIZONTAL
            }
        }

        viewModel.statsLiveData.observe(this) {
            statsAdapter.submitList(it)
        }
    }
}