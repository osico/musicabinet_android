package com.musicabinet.mobile.api

import com.musicabinet.mobile.model.home.HomeData
import com.musicabinet.mobile.model.instrument.InstrumentData
import com.musicabinet.mobile.model.instrument.matrix.InstrumentMatrixResponse
import com.musicabinet.mobile.model.instrument.matrix.filter.InstrumentFilterResponse
import com.musicabinet.mobile.model.lesson.lesson.LessonGroup
import com.musicabinet.mobile.model.lesson.machine.ToneOrChord
import com.musicabinet.mobile.model.lesson.machine.diagram.DiagramImageRequestBody
import com.musicabinet.mobile.model.lesson.machine.diagram.DiagramImageResponse
import com.musicabinet.mobile.model.lesson.machine.note.NoteItemResponse
import com.musicabinet.mobile.model.lesson.machine.note.image.NoteImageResponse
import com.musicabinet.mobile.model.lesson.machine.save.ImprovisationStaveResult
import com.musicabinet.mobile.model.lesson.progress.LessonProgress
import com.musicabinet.mobile.model.lesson.remote.LessonResponse
import com.musicabinet.mobile.model.lesson.remote.prepared.PreparedAccompaniment
import com.musicabinet.mobile.model.login.LoginRequestBody
import com.musicabinet.mobile.model.profile.UserProfile
import com.musicabinet.mobile.model.register.RegisterRequestBody
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.MultipartBody
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

    @Streaming
    @GET("/platform/api/file-storage/{fileId}/download")
    fun downloadFileWithUUID(@Path("fileId") fileId: String,
                             @Query("") randomString: String): Observable<Response<ResponseBody>>

    @GET("/api/tone")
    fun getTone(): Observable<List<ToneOrChord>>

    @GET("/api/chord-type")
    fun getChordType(): Observable<List<ToneOrChord>>

    @GET("/api/module")
    fun getNoteModule(@Query("instrumentId") instrumentId: String): Observable<NoteItemResponse>

    @GET("/api/course")
    fun getNoteCourse(@Query("instrumentId") instrumentId: String): Observable<NoteItemResponse>

    @GET("/api/diagram")
    fun getNoteDiagram(@Query("moduleId") moduleId: String,
                       @Query("courseId") courseId: String,
                       @Query("toneId") toneId: String,
                       @Query("chordTypeId") chordTypeId: String,
                       @Query("withElements") withElements: Boolean,
                       @Query("withUserStatistics") withUserStatistics: Boolean,
                       @Query("page") page: Int,
                       @Query("count") count: Int,
                       @Query("sortField") sortField: String,
                       @Query("sortDir") sortDir: String,
                       @Query("enrich") enrich: Boolean): Observable<NoteImageResponse>


    @POST("/api/diagram/find-elements")
    fun getDiagramImage(@Query("findInChords") findInChords: Boolean,
                        @Body diagramBody: DiagramImageRequestBody): Single<DiagramImageResponse>


    @POST("/api/improvisation/save")
    fun saveImprovisation(@Body body: ImprovisationStaveResult): Single<ImprovisationStaveResult>

    @Multipart
    @POST("/platform/api/file-storage/{fileId}/update")
    fun uploadImprovisation(@Path("fileId") fileId: String,
                            @Part file: MultipartBody.Part): Completable

    @GET("/api/accompaniment")
    fun getAccompaniment(@Query("activeInLibrary") activeInLibrary: Boolean,
                         @Query("instrumentId") instrumentId: String,
                         @Query("toneId") toneId: String,
                         @Query("chordTypeId") chordTypeId: String,
                         @Query("enrich") enrich: Boolean,
                         @Query("withSegments") withSegments: Boolean,
                         @Query("count") count: Int): Single<PreparedAccompaniment>

}