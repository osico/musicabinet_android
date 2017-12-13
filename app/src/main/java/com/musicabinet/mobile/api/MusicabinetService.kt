package com.musicabinet.mobile.api

import com.musicabinet.mobile.model.home.HomeData
import com.musicabinet.mobile.model.instrument.InstrumentData
import com.musicabinet.mobile.model.instrument.matrix.InstrumentMatrixResponse
import com.musicabinet.mobile.model.instrument.matrix.filter.InstrumentFilterResponse
import com.musicabinet.mobile.model.lesson.lesson.LessonGroup
import com.musicabinet.mobile.model.lesson.remote.LessonResponse
import com.musicabinet.mobile.model.login.LoginRequestBody
import com.musicabinet.mobile.model.order.OrderIdResponse
import com.musicabinet.mobile.model.order.execute.OrderExecuteBody
import com.musicabinet.mobile.model.order.execute.OrderExecuteResponse
import com.musicabinet.mobile.model.order.finish.OrderFinishExecuteBody
import com.musicabinet.mobile.model.order.finish.OrderFinishExecuteResponse
import com.musicabinet.mobile.model.profile.UserProfile
import com.musicabinet.mobile.model.register.RegisterRequestBody
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

    @POST("/api/cabinet/{productId}/order")
    fun createOrder(@Path("productId") productId: String): Observable<OrderIdResponse>

    @POST("/platform/api/order/execute")
    fun executeOrder(@Body body: OrderExecuteBody): Observable<OrderExecuteResponse>

    @POST("/platform/api/order/execute")
    fun finishExecuteOrder(@Body body: OrderFinishExecuteBody): Observable<OrderFinishExecuteResponse>

    @POST("/platform/api/user/register")
    fun registerUser(@Body registerBody: RegisterRequestBody): Completable

    @GET("/api/lesson")
    fun getLessonGroup(@Query("lessonGroupId") id: String,
                       @Query("count") count: Int): Observable<LessonGroup>

    @GET("/api/lesson/next")
    fun getNextLesson(@Query("lessonGroupId") id: String,
                      @Query("prepared") prepared: Boolean): Observable<LessonResponse>
}