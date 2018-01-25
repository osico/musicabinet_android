package com.musicabinet.mobile.ui.lessons.lesson.note

import com.musicabinet.mobile.repository.MusicabinetRepository
import com.musicabinet.mobile.repository.keyvalue.KeyValueStorage
import io.reactivex.disposables.CompositeDisposable

/**
 * @author Kirchhoff-
 */
class NotePresenter(private val repository: MusicabinetRepository,
                    private val storage: KeyValueStorage,
                    private val view: NoteContract.View) : NoteContract.Presenter {

    private val subscriptions = CompositeDisposable()
}