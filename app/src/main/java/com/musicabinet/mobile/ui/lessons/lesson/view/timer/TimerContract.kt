package com.musicabinet.mobile.ui.lessons.lesson.view.timer

/**
 * @author Kirchhoff-
 */
interface TimerContract {

    interface View {

        fun displaySecond(seconds: Long)

        fun displayMinute(minute: Long)

        fun displayHour(hour: Long)

    }

    interface Presenter {

        fun subscribe(currentTime: Long, lessonId: String)

    }
}