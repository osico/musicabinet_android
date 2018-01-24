package com.musicabinet.mobile.ui.lessons.lesson.tonechord


import com.musicabinet.mobile.model.lesson.machine.ToneOrChord
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
                BiFunction<List<ToneOrChord>, List<ToneOrChord>, Pair<List<ToneOrChord>, List<ToneOrChord>>>
                { toneList, chordList -> Pair(toneList, chordList) })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view.showLoading(true) }
                .doFinally { view.showLoading(false) }
                .subscribe({ pair: Pair<List<ToneOrChord>, List<ToneOrChord>> ->
                    view.showTone(pair.first)
                    view.showChord(pair.second)
                }, { t: Throwable -> view.showError() }))
    }


    override fun unsubscribe() {
        subscriptions.clear()
    }

}