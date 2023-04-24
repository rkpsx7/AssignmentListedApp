package com.dev_akash.assignmentlistedapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.dev_akash.assignmentlistedapp.databinding.ActivityMainBinding
import com.dev_akash.assignmentlistedapp.utils.DateTimeUtils.getMonthValueFromDate
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
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

    private val viewModel:MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

//        viewModel.getDashboardData()


        val xtr = map.map {
            Entry(getMonthValueFromDate(it.key),it.value.toFloat())
        }

        binding.chartLayout.chart.apply {
            xAxis.valueFormatter = object : ValueFormatter(){
                override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                    return when (value) {
                        0.0f -> "January"
                        1.0f -> "February"
                        2.0f -> "Mars"
                        3.0f -> "April"
                        4.0f -> "May"
                        5.0f -> "June"
                        6.0f -> "July"
                        7.0f -> "August"
                        8.0f -> "September"
                        9.0f -> "October"
                        10.0f -> "November"
                        11.0f -> "December"
                        else -> {
                            ""
                        }
//                            throw IllegalArgumentException("$value is not a valid month")
                    }
                }
            }


            val dataSet = LineDataSet(xtr,"Label1")
            dataSet.color = Color.BLUE
            dataSet.valueTextColor = Color.GREEN

            data = LineData(dataSet)
            this.invalidate()
        }

    }
}