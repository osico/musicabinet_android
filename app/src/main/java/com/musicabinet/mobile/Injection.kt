package com.musicabinet.mobile

import com.musicabinet.mobile.repository.DefaultMusicabinetRepository

/**
 * @author Kirchhoff-
 */
object Injection {

    fun provideRepository() = DefaultMusicabinetRepository

}