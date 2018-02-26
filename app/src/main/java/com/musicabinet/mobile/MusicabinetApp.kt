package com.musicabinet.mobile

import android.app.Application
import android.content.Context
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric

/**
 * @author Kirchhoff-
 */

class MusicabinetApp : Application() {

    companion object {

        private lateinit var musicApp: MusicabinetApp

        fun get(): MusicabinetApp {
            return musicApp
        }

        private lateinit var myApplicationContext: Context
        fun getAppContext(): Context {
            return myApplicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        musicApp = this
        myApplicationContext = applicationContext
        Fabric.with(this, Crashlytics())
    }
}
