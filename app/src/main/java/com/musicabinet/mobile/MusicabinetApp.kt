package com.musicabinet.mobile

import android.app.Application

/**
 * @author Kirchhoff-
 */

class MusicabinetApp : Application() {


    override fun onCreate() {
        super.onCreate()

        //Fabric.with(this, Crashlytics())
    }
}
