package com.dev_akash.assignmentlistedapp.di

import com.dev_akash.assignmentlistedapp.utils.Constants.BASE_URL
import com.dev_akash.assignmentlistedapp.network.AuthInterceptor
import com.dev_akash.assignmentlistedapp.network.DashBoardApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesDashBoardApi(
        retrofitBuilder: Retrofit.Builder,
        okHttpClient: OkHttpClient
    ): DashBoardApi {
        return retrofitBuilder.client(okHttpClient).build().create(DashBoardApi::class.java)
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(authInterceptor).build()
    }

    @Singleton
    @Provides
    fun providesRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }
}