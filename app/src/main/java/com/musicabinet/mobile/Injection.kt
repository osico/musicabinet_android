package com.musicabinet.mobile

import com.musicabinet.mobile.repository.DefaultMusicabinetRepository
import com.musicabinet.mobile.repository.keyvalue.MusicabinetKeyValueStorage

/**
 * @author Kirchhoff-
 */
object Injection {

    fun provideRepository() = DefaultMusicabinetRepository

    fun provideStorage() = MusicabinetKeyValueStorage

}