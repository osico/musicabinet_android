package com.musicabinet.mobile

import android.app.Application
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
    }

    override fun onCreate() {
        super.onCreate()

        musicApp = this
        Fabric.with(this, Crashlytics())
    }
}
