package com.musicabinet.mobile.ui.lessons.lesson.view.timer

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.musicabinet.mobile.Injection
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.displayTimeValue
import com.musicabinet.mobile.extensions.setVisible
import kotlinx.android.synthetic.main.view_timer.view.*

/**
 * @author Kirchhoff-
 */
class TimerView : LinearLayout, TimerContract.View {

    private val presenter = TimerPresenter(this, Injection.provideRepository())

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        LayoutInflater.from(context).inflate(R.layout.view_timer, this, true)
    }

    fun setStartTime(time: Long, lessonId: String) {
        presenter.subscribe(time, lessonId)
    }

    override fun displaySecond(seconds: Long) {
        tvSeconds.displayTimeValue(seconds)
    }

    override fun displayMinute(minute: Long) {
        tvMinutes.displayTimeValue(minute)
    }

    override fun displayHour(hour: Long) {
        tvHours.displayTimeValue(hour)
    }

    override fun showTime(show: Boolean) {
        timerLayout.setVisible(show)
    }
}