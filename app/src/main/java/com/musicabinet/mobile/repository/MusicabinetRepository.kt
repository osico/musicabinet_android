package com.musicabinet.mobile.repository

import com.musicabinet.mobile.model.home.HomeData
import com.musicabinet.mobile.model.instrument.InstrumentData
import com.musicabinet.mobile.model.profile.UserProfile
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * @author Kirchhoff-
 */
interface MusicabinetRepository {

    fun getHomeNews(start: Int): Observable<HomeData>

    fun getHomeTutorial(start: Int): Observable<HomeData>

    fun getHomeVideo(start: Int): Observable<HomeData>

    fun getInstrumentList(): Observable<InstrumentData>

    fun login(email: String, password: String): Completable

    fun getUserProfile(): Observable<UserProfile>
}