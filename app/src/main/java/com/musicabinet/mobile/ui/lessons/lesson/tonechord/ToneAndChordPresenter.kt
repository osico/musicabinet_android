package com.musicabinet.mobile.ui.lessons.lesson.tonechord

import com.musicabinet.mobile.model.lesson.machine.Chord
import com.musicabinet.mobile.model.lesson.machine.Tone
import com.musicabinet.mobile.repository.MusicabinetRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction

/**
 * @author Kirchhoff-
 */
class ToneAndChordPresenter(private val view: ToneAndChordContract.View,
                            private val repository: MusicabinetRepository) : ToneAndChordContract.Presenter {

    private val subscriptions = CompositeDisposable()

    override fun subscribe() {

        subscriptions.add(Observable.zip(repository.getTone(), repository.getChordType(),
                BiFunction<List<Tone>, List<Chord>, Pair<List<Tone>, List<Chord>>>
                { toneList, chordList -> Pair(toneList, chordList) })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view.showLoading(true) }
                .doFinally { view.showLoading(false) }
                .subscribe({ pair: Pair<List<Tone>, List<Chord>> ->
                    view.showTone(pair.first)
                    view.showChord(pair.second)
                }, { t: Throwable -> view.showError() }))
    }


    override fun unsubscribe() {
        subscriptions.clear()
    }

}