package com.dev_akash.assignmentlistedapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev_akash.assignmentlistedapp.data.Resource
import com.dev_akash.assignmentlistedapp.repository.DashboardRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repo: DashboardRepo
):ViewModel() {


    fun getDashboardData(){
        viewModelScope.launch {
//            val res = repo.getDashboardData()

//            when(res.status){
//                Resource.Status.SUCCESS->{
//
//                }
//                Resource.Status.ERROR->{
//
//                }
//                else -> {}
//            }
        }
    }


}