package com.musicabinet.mobile.ui.lesson.list

import com.musicabinet.mobile.repository.MusicabinetRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

/**
 * @author Kirchhoff-
 */
class LessonListPresenter(private val view: LessonListContract.View,
                          private val repository: MusicabinetRepository) : LessonListContract.Presenter {

    private val subscriptions = CompositeDisposable()

    override fun getFilters(instrumentId: String) {
        subscriptions.add(repository.getInstrumentMatrixFilter(instrumentId)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view.showLoading(true) }
                .doOnTerminate { view.showLoading(false) }
                .subscribe({ view.showSuccess() }, { view.showError() }))
    }

}