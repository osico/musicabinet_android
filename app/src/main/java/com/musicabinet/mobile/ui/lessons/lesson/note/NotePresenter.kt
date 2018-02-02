package com.musicabinet.mobile.ui.lessons.lesson.note

import com.musicabinet.mobile.model.lesson.machine.ToneOrChordResult
import com.musicabinet.mobile.model.lesson.machine.note.NoteItem
import com.musicabinet.mobile.model.lesson.machine.note.NoteItemResponse
import com.musicabinet.mobile.model.lesson.machine.note.image.NoteElement
import com.musicabinet.mobile.model.lesson.machine.note.image.NoteImageResponse
import com.musicabinet.mobile.repository.MusicabinetRepository
import com.musicabinet.mobile.repository.keyvalue.KeyValueStorage
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import java.util.*

/**
 * @author Kirchhoff-
 */
class NotePresenter(private val repository: MusicabinetRepository,
                    private val storage: KeyValueStorage,
                    private val view: NoteContract.View) : NoteContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private var moduleId: String? = null
    private var courseId: String? = null

    override fun subscribe(toneOrChordResult: ToneOrChordResult, moduleId: String?,
                           courseId: String?) {
        this.moduleId = moduleId
        this.courseId = courseId
        subscriptions.add(Observable.zip(repository.getNoteModule(storage.getSelectedInstrumentId()),
                repository.getNoteCourse(storage.getSelectedInstrumentId()),
                BiFunction<NoteItemResponse, NoteItemResponse, Pair<List<NoteItem>, List<NoteItem>>>
                { noteModule, noteCourse ->
                    Collections.sort(noteModule.noteList)
                    Collections.sort(noteCourse.noteList)
                    Pair(noteModule.noteList, noteCourse.noteList)
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view.showLoading(true) }
                .subscribe({ pair: Pair<List<NoteItem>, List<NoteItem>> ->
                    view.showModule(pair.first, moduleId)
                    view.showInstrument(pair.second, courseId)
                    if (moduleId != null && courseId != null)
                        getNoteDiagram(toneOrChordResult, moduleId, courseId)
                    else if (!pair.first.isEmpty() && !pair.second.isEmpty())
                        getNoteDiagram(toneOrChordResult, pair.first[0].id, pair.second[0].id)
                    else
                        view.showLoading(false)
                }, { t: Throwable -> view.showError() }))
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    fun getNoteDiagram(toneOrChordResult: ToneOrChordResult, moduleId: String,
                       courseId: String) {
        this.moduleId = moduleId
        this.courseId = courseId
        subscriptions.add(repository.getNoteDiagram(moduleId, courseId, toneOrChordResult.tone.id,
                toneOrChordResult.chord.id)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    view.showLoading(false)
                    view.showNoteLoading(true)
                }
                .subscribe({ imageResponse: NoteImageResponse ->
                    val noteImageList = ArrayList<NoteElement>()
                    for (item in imageResponse.list) {
                        noteImageList.addAll(item.list)
                    }
                    view.showNoteImage(noteImageList)
                    view.showNoteLoading(false)
                }, { t: Throwable ->
                    view.showError()
                }))
    }

    override fun getModuleId() = moduleId

    override fun getCourseId() = courseId

}