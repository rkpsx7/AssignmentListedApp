package com.dev_akash.assignmentlistedapp.network

import com.dev_akash.assignmentlistedapp.data.DashBoardResponse
import retrofit2.Response
import retrofit2.http.GET

interface DashBoardApi {


    @GET("api/v1/dashboardNew")
    suspend fun getDashboardData():Response<DashBoardResponse>
}