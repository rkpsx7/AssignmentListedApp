package com.dev_akash.assignmentlistedapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev_akash.assignmentlistedapp.data.DashBoardResponse
import com.dev_akash.assignmentlistedapp.data.LinksDto
import com.dev_akash.assignmentlistedapp.data.Resource
import com.dev_akash.assignmentlistedapp.data.Stats
import com.dev_akash.assignmentlistedapp.repository.DashboardRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repo: DashboardRepo
) : ViewModel() {

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
        viewModelScope.launch {
            val res = repo.getDashboardData()

            when (res.status) {
                Resource.Status.SUCCESS -> {
                    prepareStatsList(res.data)
                    setTopLinks(res.data)
                    setRecentLinks(res.data)
                }
                Resource.Status.ERROR -> {

                }
                else -> {}
            }
        }
    }

    private fun setRecentLinks(data: DashBoardResponse?) {
        viewModelScope.launch {
            data?.data?.let {
                _recentLinksLiveData.postValue(it.recentLinks)
            }
        }
    }

    private fun setTopLinks(data: DashBoardResponse?) {
        viewModelScope.launch {
            data?.data?.let {
                _topLinksLiveData.postValue(it.topLinks)
            }
        }
    }

    private fun prepareStatsList(data: DashBoardResponse?) {
        viewModelScope.launch {
            val statList = ArrayList<Stats>()
            data?.let {
                val location = if (it.topLocation.isNullOrEmpty()) "N/A" else it.topLocation.toString()
                val source = if (it.topLocation.isNullOrEmpty()) "N/A" else it.topSource.toString()

                statList.add(
                    Stats(R.drawable.ic_todays_click, "${it.todayClicks ?: "N/A"}", "Today's click"))
                statList.add(Stats(R.drawable.ic_location, location, "Top Location"))
                statList.add(Stats(R.drawable.ic_source, source, "Top Source"))
            }

            _statsLiveData.postValue(statList)
        }
    }


}