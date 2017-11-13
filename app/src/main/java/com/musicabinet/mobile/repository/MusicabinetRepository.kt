package com.musicabinet.mobile.repository

import com.musicabinet.mobile.model.home.HomeData
import io.reactivex.Observable

/**
 * @author Kirchhoff-
 */
interface MusicabinetRepository {

    fun getHomeNews(start: Int): Observable<HomeData>

    fun getHomeTutorial(start: Int): Observable<HomeData>

    fun getHomeVideo(start: Int): Observable<HomeData>
}