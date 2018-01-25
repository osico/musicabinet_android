package com.musicabinet.mobile.ui.lessons.lesson.note

import com.musicabinet.mobile.model.lesson.machine.note.NoteItem
import com.musicabinet.mobile.model.lesson.machine.note.NoteItemResponse
import com.musicabinet.mobile.repository.MusicabinetRepository
import com.musicabinet.mobile.repository.keyvalue.KeyValueStorage
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction

/**
 * @author Kirchhoff-
 */
class NotePresenter(private val repository: MusicabinetRepository,
                    private val storage: KeyValueStorage,
                    private val view: NoteContract.View) : NoteContract.Presenter {

    private val subscriptions = CompositeDisposable()

    override fun subscribe() {
        subscriptions.add(Observable.zip(repository.getNoteModule(storage.getSelectedInstrumentId()),
                repository.getNoteCourse(storage.getSelectedInstrumentId()),
                BiFunction<NoteItemResponse, NoteItemResponse, Pair<List<NoteItem>, List<NoteItem>>>
                { noteModule, noteCourse -> Pair(noteModule.noteList, noteCourse.noteList) })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view.showLoading(true) }
                .subscribe({ pair: Pair<List<NoteItem>, List<NoteItem>> ->
                    view.showInstrument(pair.first)
                    view.showModule(pair.second)
                    view.showLoading(false)
                }, { t: Throwable -> view.showError() }))
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

}