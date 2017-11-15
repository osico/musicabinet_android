package com.musicabinet.mobile.api

import com.musicabinet.mobile.model.home.HomeData
import com.musicabinet.mobile.model.instrument.InstrumentData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Kirchhoff-
 */
interface MusicabinetService {

    @GET("/platform/api/content/element")
    fun getHomeItems(@Query("sectionIds") id: String, @Query("active") active: Boolean,
                     @Query("start") start: Int, @Query("count") count: Int): Observable<HomeData>


    @GET("/api/instrument")
    fun getInstrumentList(): Observable<InstrumentData>
}