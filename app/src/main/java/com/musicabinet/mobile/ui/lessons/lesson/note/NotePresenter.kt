package com.musicabinet.mobile.ui.lessons.lesson.note

import com.musicabinet.mobile.model.lesson.machine.ToneOrChord
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

        if (toneOrChordResult.chord.id.isEmpty() && toneOrChordResult.tone.id.isEmpty()) {

            subscriptions.add(Observable.zip(
                    repository.getTone(), repository.getChordType(),
                    BiFunction<List<ToneOrChord>, List<ToneOrChord>, Pair<List<ToneOrChord>, List<ToneOrChord>>>
                    { toneList, chordList ->
                        Collections.sort(toneList)
                        Collections.sort(chordList)
                        Pair(toneList, chordList)
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { view.showLoading(true) }
                    .subscribe({ pair: Pair<List<ToneOrChord>, List<ToneOrChord>> ->

                        val toneList = pair.first
                        val chordList = pair.second

                        val toneItem = findItemInList(toneList, toneOrChordResult.tone.name)
                        val chordItem = findItemInList(chordList, toneOrChordResult.chord.name)

                        if (toneItem != null && chordItem != null) {
                            getModuleAndCourse(ToneOrChordResult(toneItem, chordItem))
                        } else {
                            view.showError()
                        }

                    }, { t: Throwable -> view.showError() }))

        } else {
            getModuleAndCourse(toneOrChordResult)
        }
    }

    private fun findItemInList(list: List<ToneOrChord>, code: String): ToneOrChord? {
        for (item in list) {
            if (item.code.equals(code, true))
                return item
        }

        return null
    }

    private fun getModuleAndCourse(toneOrChordResult: ToneOrChordResult) {
        subscriptions.add(Observable.zip(repository.getNoteModule(storage.getSelectedInstrumentId()),
                repository.getNoteCourse(storage.getSelectedInstrumentId()),
                BiFunction<NoteItemResponse, NoteItemResponse, Pair<List<NoteItem>, List<NoteItem>>>
                { noteModule, noteCourse ->
                    Collections.sort(noteModule.noteList)
                    Collections.sort(noteCourse.noteList)
                    noteModule.noteList
                            .filter { it.activeInLibrary != null && !it.activeInLibrary }
                            .forEach { noteModule.noteList.remove(it) }

                    noteCourse.noteList
                            .filter { it.activeInLibrary != null && !it.activeInLibrary }
                            .forEach { noteCourse.noteList.remove(it) }
                    Pair(noteModule.noteList, noteCourse.noteList)
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view.showLoading(true) }
                .subscribe({ pair: Pair<List<NoteItem>, List<NoteItem>> ->
                    view.showModule(pair.first, moduleId)
                    view.showInstrument(pair.second, courseId)
                    if (moduleId != null && courseId != null)
                        getNoteDiagram(toneOrChordResult, moduleId!!, courseId!!)
                    else if (!pair.first.isEmpty() && !pair.second.isEmpty())
                        getNoteDiagram(toneOrChordResult, pair.first[0].id, pair.second[0].id)
                    else
                        view.showLoading(false)
                }, { t: Throwable -> view.showError() }))
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

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun getModuleId() = moduleId

    override fun getCourseId() = courseId

}