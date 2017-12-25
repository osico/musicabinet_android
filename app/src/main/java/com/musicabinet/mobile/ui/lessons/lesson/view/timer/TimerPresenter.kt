package com.musicabinet.mobile.ui.lessons.lesson.view.timer

/**
 * @author Kirchhoff-
 */
class TimerPresenter(private val view: TimerContract.View) : TimerContract.Presenter {

    private val SECOND = 1000
    private val MINUTE = SECOND * 60
    private val HOUR = MINUTE * 60


    override fun subscribe(currentTime: Long) {
        var bufTime = currentTime - (currentTime / HOUR) * HOUR

        view.displayHour(currentTime / HOUR)

        view.displayMinute(bufTime / MINUTE)
        bufTime -= (bufTime / MINUTE) * MINUTE

        view.displaySecond(bufTime / SECOND)
    }

}