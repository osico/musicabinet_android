package com.musicabinet.mobile

import android.app.Application

import com.crashlytics.android.Crashlytics

import io.fabric.sdk.android.Fabric

/**
 * @author Kirchhoff-
 */

class MusicabinetApp : Application() {


    override fun onCreate() {
        super.onCreate()

        Fabric.with(this, Crashlytics())
    }
}
