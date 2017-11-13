package com.musicabinet.mobile.api

import com.musicabinet.mobile.BuildConfig
import com.musicabinet.mobile.api.interceptor.LogginingInterceptor
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Kirchhoff-
 */
object ApiFactory {

    private fun buildClient(): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(LogginingInterceptor())
                    .build()

    private var retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(buildClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }

    public var service: MusicabinetService

    init {
        service = retrofit.create(MusicabinetService::class.java)
    }

}