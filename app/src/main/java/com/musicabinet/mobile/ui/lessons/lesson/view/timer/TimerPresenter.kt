package com.musicabinet.mobile.ui.lessons.lesson.view.timer

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

/**
 * @author Kirchhoff-
 */
class TimerPresenter(private val view: TimerContract.View) : TimerContract.Presenter {

    private val SECOND = 1000
    private val MINUTE = SECOND * 60
    private val HOUR = MINUTE * 60


    override fun subscribe(currentTime: Long) {
        displayTime(currentTime)

        var sum = 0
        Observable.just(currentTime)
                .map {
                    sum += 1000
                    currentTime + sum
                }
                .repeatWhen { it.delay(1, TimeUnit.SECONDS) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ displayTime(it) })
    }


    private fun displayTime(currentTime: Long) {
        var bufTime = currentTime - (currentTime / HOUR) * HOUR

        view.displayHour(currentTime / HOUR)

        view.displayMinute(bufTime / MINUTE)
        bufTime -= (bufTime / MINUTE) * MINUTE

        view.displaySecond(bufTime / SECOND)
    }

}