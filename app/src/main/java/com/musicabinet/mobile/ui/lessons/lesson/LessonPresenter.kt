package com.musicabinet.mobile.ui.lessons.lesson

import com.musicabinet.mobile.repository.MusicabinetRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

/**
 * @author Kirchhoff-
 */
class LessonPresenter(private val view: LessonContract.View,
                      private val repository: MusicabinetRepository) : LessonContract.Presenter {

    private val subscriptions = CompositeDisposable()

    override fun getLessonGroup(id: String) {
        subscriptions.add(repository.getLessonGroup(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view.showSuccess() }, { view.showError() }))
    }

}