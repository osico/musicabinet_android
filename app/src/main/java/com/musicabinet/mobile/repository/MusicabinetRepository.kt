package com.musicabinet.mobile.repository

import com.musicabinet.mobile.model.home.HomeData
import com.musicabinet.mobile.model.instrument.InstrumentData
import com.musicabinet.mobile.model.instrument.matrix.InstrumentMatrixResponse
import com.musicabinet.mobile.model.instrument.matrix.filter.InstrumentFilterResponse
import com.musicabinet.mobile.model.lesson.LessonGroup
import com.musicabinet.mobile.model.lesson.LessonResponse
import com.musicabinet.mobile.model.order.OrderIdResponse
import com.musicabinet.mobile.model.order.execute.OrderExecuteResponse
import com.musicabinet.mobile.model.order.finish.OrderFinishExecuteResponse
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

    fun getInstrumentMatrix(instrumentId: String): Observable<InstrumentMatrixResponse>

    fun getInstrumentMatrixFilter(instrumentId: String): Observable<InstrumentFilterResponse>

    fun createOrder(orderId: String): Observable<OrderIdResponse>

    fun executeOrder(orderId: String): Observable<OrderExecuteResponse>

    fun finishExecuteOrder(orderId: String, nonce: String): Observable<OrderFinishExecuteResponse>

    fun registerUser(email: String, password: String, name: String, surname: String): Completable

    fun getLessonGroup(id: String): Observable<LessonGroup>

    fun getNextLesson(id: String): Observable<LessonResponse>
}