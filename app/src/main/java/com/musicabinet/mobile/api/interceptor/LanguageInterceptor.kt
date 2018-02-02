package com.musicabinet.mobile.api.interceptor

/**
 * @author Kirchhoff-
 */

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.*

/**
 * @author Kirchhoff-
 */

class LanguageInterceptor : Interceptor {

    companion object {

        private val HEADER_LANGUAGE = "Accept-Language"
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {


        val request = chain.request()
                .newBuilder()
                .addHeader(HEADER_LANGUAGE, Locale.getDefault().language)
                .build()

        return chain.proceed(request)
    }
}