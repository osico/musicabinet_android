package com.musicabinet.mobile

import android.app.Application

/**
 * @author Kirchhoff-
 */

class MusicabinetApp : Application() {

    companion object {

        private lateinit var musicApp: MusicabinetApp

        fun get(): MusicabinetApp {
            return musicApp
        }
    }

    override fun onCreate() {
        super.onCreate()

        musicApp = this
        //Fabric.with(this, Crashlytics())
    }
}
