package com.dev_akash.assignmentlistedapp.network

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody

class NetworkConnectionInterceptor(val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()

        return if (!isConnected) {
            Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(502)
                .message("Network Error")
                .body(ResponseBody.create(null, "{Network Not Connected}")).build()
        } else {
            try {
                val builder = request.newBuilder()
                chain.proceed(builder.build())
            } catch (exception: Exception) {
                Response.Builder()
                    .request(request)
                    .protocol(Protocol.HTTP_1_1)
                    .code(502)
                    .message(exception.message ?: "Unknown Error")
                    .body(ResponseBody.create(null, "{${exception}}")).build()
            }
        }
    }

    private val isConnected: Boolean
        get() {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = connectivityManager.activeNetworkInfo
            return netInfo != null && netInfo.isConnected
        }

}