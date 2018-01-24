package com.musicabinet.mobile.api

import com.musicabinet.mobile.model.home.HomeData
import com.musicabinet.mobile.model.instrument.InstrumentData
import com.musicabinet.mobile.model.instrument.matrix.InstrumentMatrixResponse
import com.musicabinet.mobile.model.instrument.matrix.filter.InstrumentFilterResponse
import com.musicabinet.mobile.model.lesson.lesson.LessonGroup
import com.musicabinet.mobile.model.lesson.machine.Chord
import com.musicabinet.mobile.model.lesson.machine.Tone
import com.musicabinet.mobile.model.lesson.progress.LessonProgress
import com.musicabinet.mobile.model.lesson.remote.LessonResponse
import com.musicabinet.mobile.model.login.LoginRequestBody
import com.musicabinet.mobile.model.profile.UserProfile
import com.musicabinet.mobile.model.register.RegisterRequestBody
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

/**
 * @author Kirchhoff-
 */
interface MusicabinetService {

    @GET("/platform/api/content/element")
    fun getHomeItems(@Query("sectionIds") id: String,
                     @Query("active") active: Boolean,
                     @Query("start") start: Int,
                     @Query("count") count: Int,
                     @Query("sortField") sortField: String,
                     @Query("sortDir") sortDir: String): Observable<HomeData>

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

    @POST("/platform/api/user/register")
    fun registerUser(@Body registerBody: RegisterRequestBody): Completable

    @GET("/api/lesson")
    fun getLessonGroup(@Query("lessonGroupId") id: String,
                       @Query("count") count: Int): Observable<LessonGroup>

    @GET("/api/lesson/next")
    fun getNextLesson(@Query("lessonGroupId") id: String,
                      @Query("prepared") prepared: Boolean): Observable<LessonResponse>

    @GET("/api/lesson/{lessonId}/prepared")
    fun getPreparedLesson(@Path("lessonId") lessonId: String): Observable<LessonResponse>

    @POST("/api/history/{lessonId}/update-progress")
    fun updateLessonProgress(@Path("lessonId") lessonId: String,
                             @Body lessonProgress: LessonProgress): Completable

    @Streaming
    @GET("/platform/api/file-storage/{fileId}/download")
    fun downloadFile(@Path("fileId") fileId: String): Observable<Response<ResponseBody>>

    @GET("/api/tone")
    fun getTone(): Single<List<Tone>>

    @GET("/api/chord-type")
    fun getChordType(): Single<List<Chord>>
}