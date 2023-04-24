package com.dev_akash.assignmentlistedapp.repository

import com.dev_akash.assignmentlistedapp.data.DashBoardResponse
import com.dev_akash.assignmentlistedapp.data.Resource
import com.dev_akash.assignmentlistedapp.network.DashBoardApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DashboardRepo @Inject constructor(val dashBoardApi: DashBoardApi) {


    suspend fun getDashboardData(): Resource<DashBoardResponse?> {
        val res = dashBoardApi.getDashboardData()

        return if (res.isSuccessful && res.code() == 200){
            Resource.success(res.body())
        }else Resource.error(res.message()?:"")
    }
}