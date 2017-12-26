package com.musicabinet.mobile.ui.lessons.lesson.view.timer

import com.musicabinet.mobile.repository.MusicabinetRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

/**
 * @author Kirchhoff-
 */
class TimerPresenter(private val view: TimerContract.View,
                     private val repository: MusicabinetRepository) : TimerContract.Presenter {

    private val SECOND = 1000
    private val MINUTE = SECOND * 60
    private val HOUR = MINUTE * 60

    private val subscriptions = CompositeDisposable()

    override fun subscribe(currentTime: Long, lessonId: String) {
        displayTime(currentTime)

        var sum = 0
        Observable.just(currentTime)
                .map {
                    sum -= 1000
                    currentTime + sum
                }
                .repeatWhen { it.delay(1, TimeUnit.SECONDS) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ displayTime(it) })


        subscriptions.add(Observable.just(true)
                .delay(30, TimeUnit.SECONDS)
                .repeat()
                .subscribe({ updateProgress(lessonId) }))
    }

    private fun updateProgress(lessonId: String) {
        subscriptions.add(repository.updateLessonProgress(lessonId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }

    private fun displayTime(currentTime: Long) {

        view.showTime(currentTime > 0)

        var bufTime = currentTime - (currentTime / HOUR) * HOUR

        view.displayHour(currentTime / HOUR)

        view.displayMinute(bufTime / MINUTE)
        bufTime -= (bufTime / MINUTE) * MINUTE

        view.displaySecond(bufTime / SECOND)
    }

}