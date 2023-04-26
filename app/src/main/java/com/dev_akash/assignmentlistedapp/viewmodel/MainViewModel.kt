package com.dev_akash.assignmentlistedapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev_akash.assignmentlistedapp.R
import com.dev_akash.assignmentlistedapp.model.DashBoardResponse
import com.dev_akash.assignmentlistedapp.model.LinksDto
import com.dev_akash.assignmentlistedapp.model.Resource
import com.dev_akash.assignmentlistedapp.model.Stats
import com.dev_akash.assignmentlistedapp.repository.DashboardRepo
import com.dev_akash.assignmentlistedapp.utils.Constants.SUPPORT_WHATSAPP_NUMBER
import com.dev_akash.assignmentlistedapp.utils.DateTimeUtils.getMonthValueFromDate
import com.dev_akash.assignmentlistedapp.utils.SharedPrefs
import com.dev_akash.assignmentlistedapp.utils.getStringResource
import com.dev_akash.assignmentlistedapp.utils.ioJob
import com.github.mikephil.charting.data.Entry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repo: DashboardRepo
) : ViewModel() {

    private val _chartsLiveData = MutableLiveData<List<Entry>?>()
    val chartsLiveData: LiveData<List<Entry>?>
        get() = _chartsLiveData

    private val _statsLiveData = MutableLiveData<List<Stats>>()
    val statsLiveData: LiveData<List<Stats>>
        get() = _statsLiveData

    private val _topLinksLiveData = MutableLiveData<List<LinksDto>>()
    val topLinksLiveData: LiveData<List<LinksDto>>
        get() = _topLinksLiveData

    private val _recentLinksLiveData = MutableLiveData<List<LinksDto>>()
    val recentLinksLiveData: LiveData<List<LinksDto>>
        get() = _recentLinksLiveData

    fun getDashboardData() {
        ioJob {
            val res = repo.getDashboardData()

            when (res.status) {
                Resource.Status.SUCCESS -> {
                    prepareChartData(res.data?.data?.overallUrlChart)
                    saveSupportNumber(res.data?.supportWhatsappNumber)
                    prepareStatsList(res.data)
                    postLinksData(res.data)
                }
                Resource.Status.ERROR -> {
                    _chartsLiveData.postValue(null)
                }
            }
        }
    }

    private fun prepareChartData(overallUrlChart: HashMap<String, Int>?) {
        viewModelScope.launch {
            overallUrlChart?.let { data ->
                val finalEntries = getMonthWiseEntries()
                val monthWiseClickData = HashMap<Float, Int>()

                data.forEach { entry ->
                    val key = getMonthValueFromDate(entry.key)
                    val prevValue = monthWiseClickData[key]
                    prevValue?.let {
                        monthWiseClickData[key] = entry.value.plus(prevValue)
                    } ?: kotlin.run {
                        monthWiseClickData[key] = entry.value
                    }
                }

                monthWiseClickData.forEach { mapEntry ->
                    finalEntries.find { it.x == mapEntry.key }?.y = mapEntry.value.toFloat()
                }

                _chartsLiveData.postValue(finalEntries)

            }
        }

    }

    private fun getMonthWiseEntries(): ArrayList<Entry> {
        val monthList: ArrayList<Entry> = ArrayList()
        monthList.add(Entry(0f, 0f)) // to leave first cell empty in chart
        monthList.add(Entry(1f, 0f))
        monthList.add(Entry(2f, 0f))
        monthList.add(Entry(3f, 0f))
        monthList.add(Entry(4f, 0f))
        monthList.add(Entry(5f, 0f))
        monthList.add(Entry(6f, 0f))
        monthList.add(Entry(7f, 0f))
        monthList.add(Entry(8f, 0f))
        monthList.add(Entry(9f, 0f))
        monthList.add(Entry(10f, 0f))
        monthList.add(Entry(11f, 0f))
        monthList.add(Entry(12f, 0f))

        return monthList
    }

    private fun saveSupportNumber(supportWhatsappNumber: String?) {
        SharedPrefs.setStringParam(SUPPORT_WHATSAPP_NUMBER, supportWhatsappNumber)
    }

    private fun postLinksData(data: DashBoardResponse?) {
        viewModelScope.launch {
            data?.data?.let {
                _recentLinksLiveData.postValue(it.recentLinks)
                _topLinksLiveData.postValue(it.topLinks)
            }
        }
    }

    private fun prepareStatsList(data: DashBoardResponse?) {
        viewModelScope.launch {
            val statList = ArrayList<Stats>()
            data?.let {
                val location = if (it.topLocation.isNullOrEmpty()) getStringResource(R.string.n_a) else it.topLocation.toString()
                val source = if (it.topLocation.isNullOrEmpty()) getStringResource(R.string.n_a) else it.topSource.toString()

                statList.add(Stats(
                    R.drawable.ic_todays_click,
                    "${it.todayClicks ?: getStringResource(R.string.n_a)}",
                    getStringResource(R.string.tofays_click)))

                statList.add(Stats(R.drawable.ic_location, location, getStringResource(R.string.top_location)))
                statList.add(Stats(R.drawable.ic_source, source, getStringResource(R.string.top_source)))
            }

            _statsLiveData.postValue(statList)
        }
    }


}