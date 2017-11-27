package com.musicabinet.mobile.api

import com.musicabinet.mobile.model.home.HomeData
import com.musicabinet.mobile.model.instrument.InstrumentData
import com.musicabinet.mobile.model.profile.UserProfile
import com.musicabinet.mobile.model.request.LoginRequestBody
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
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

    @POST("/platform/api/user/login")
    fun login(@Body loginBody: LoginRequestBody): Completable

    @POST("/platform/api/user/logged")
    fun getUserProfile(): Observable<UserProfile>
}