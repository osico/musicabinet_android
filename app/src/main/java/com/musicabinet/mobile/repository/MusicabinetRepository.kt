package com.musicabinet.mobile.repository

import com.musicabinet.mobile.model.home.HomeData
import com.musicabinet.mobile.model.instrument.InstrumentData
import com.musicabinet.mobile.model.instrument.matrix.InstrumentMatrixResponse
import com.musicabinet.mobile.model.instrument.matrix.filter.InstrumentFilterResponse
import com.musicabinet.mobile.model.lesson.lesson.LessonGroup
import com.musicabinet.mobile.model.lesson.machine.ToneOrChord
import com.musicabinet.mobile.model.lesson.machine.diagram.DiagramImageResponse
import com.musicabinet.mobile.model.lesson.machine.note.NoteItemResponse
import com.musicabinet.mobile.model.lesson.machine.note.image.NoteImageResponse
import com.musicabinet.mobile.model.lesson.machine.save.ImprovisationStaveResult
import com.musicabinet.mobile.model.lesson.remote.LessonResponse
import com.musicabinet.mobile.model.lesson.remote.prepared.PreparedAccompaniment
import com.musicabinet.mobile.model.profile.UserProfile
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.File

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

    fun registerUser(email: String, password: String, name: String, surname: String): Completable

    fun getLessonGroup(id: String): Observable<LessonGroup>

    fun getNextLesson(id: String): Observable<LessonResponse>

    fun getPreparedLesson(id: String): Observable<LessonResponse>

    fun updateLessonProgress(id: String): Completable

    fun downloadFile(fileId: String): Observable<Response<ResponseBody>>

    fun downloadFileWithUUID(fileId: String): Observable<Response<ResponseBody>>

    fun getTone(): Observable<List<ToneOrChord>>

    fun getChordType(): Observable<List<ToneOrChord>>

    fun getNoteModule(id: String): Observable<NoteItemResponse>

    fun getNoteCourse(id: String): Observable<NoteItemResponse>

    fun getNoteDiagram(moduleId: String, courseId: String, toneId: String,
                       chordTypeId: String): Observable<NoteImageResponse>

    fun getDiagramImage(diagramString: String): Single<DiagramImageResponse>

    fun saveImprovisation(id: String): Single<ImprovisationStaveResult>

    fun uploadImprovisation(id: String, file: File): Completable

    fun getAccompaniment(instrumentId: String, toneId: String, chordTypeId: String): Single<PreparedAccompaniment>
}