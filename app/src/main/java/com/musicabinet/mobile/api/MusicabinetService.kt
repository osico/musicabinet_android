package com.musicabinet.mobile.api

import com.musicabinet.mobile.model.home.HomeData
import com.musicabinet.mobile.model.instrument.InstrumentData
import com.musicabinet.mobile.model.instrument.matrix.InstrumentMatrixResponse
import com.musicabinet.mobile.model.instrument.matrix.filter.InstrumentFilterResponse
import com.musicabinet.mobile.model.profile.UserProfile
import com.musicabinet.mobile.model.request.LoginRequestBody
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.*

/**
 * @author Kirchhoff-
 */
interface MusicabinetService {

    @GET("/platform/api/content/element")
    fun getHomeItems(@Query("sectionIds") id: String, @Query("active") active: Boolean,
                     @Query("start") start: Int, @Query("count") count: Int): Observable<HomeData>

    @GET("/api/instrument")
    fun getInstrumentList(): Observable<InstrumentData>

    @POST("/platform/api/user/login")
    fun login(@Body loginBody: LoginRequestBody): Completable

    @POST("/platform/api/user/logged")
    fun getUserProfile(): Observable<UserProfile>

    @GET("/api/instrument/{id}/matrix")
    fun getInstrumentMatrix(@Path("id") instrumentId: String): Observable<InstrumentMatrixResponse>

    @GET("/api/program")
    fun getInstrumentMatrixFilter(@Query("instrumentId") instrumentId: String,
                                  @Query("active") active: Boolean,
                                  @Query("start") start: Int,
                                  @Query("count") count: Int): Observable<InstrumentFilterResponse>
}