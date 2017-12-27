package com.musicabinet.mobile.api

import com.musicabinet.mobile.BuildConfig
import com.musicabinet.mobile.MusicabinetApp
import com.musicabinet.mobile.api.cookie.WebviewCookieHandler
import com.musicabinet.mobile.api.interceptor.LogginingInterceptor
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Kirchhoff-
 */
object ApiFactory {

    private var cookieManager = WebviewCookieHandler()

    private val cache = Cache(MusicabinetApp.get().cacheDir, 10 * 1024 * 2014)

    private fun buildClient(): OkHttpClient =
            OkHttpClient.Builder()
                    .cookieJar(cookieManager)
                    .cache(cache)
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

    var service: MusicabinetService

    init {
        service = retrofit.create(MusicabinetService::class.java)
    }

    fun clearCookie() {
        cookieManager.clearCookie()
    }
}