package com.musicabinet.mobile.ui.view.metronome

import android.content.Context
import android.graphics.PorterDuff
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import com.musicabinet.mobile.R
import kotlinx.android.synthetic.main.view_metronome.view.*

/**
 * @author Kirchhoff-
 */
class MetronomeView : ConstraintLayout, MetronomeContract.View {

    private val presenter = MetronomePresenter(this)

    private lateinit var mediaPlayer: MediaPlayer
    private var tickRunnable: Runnable? = null
    private lateinit var tickHandler: Handler
    private var listener: OnMetronomeStatusChange? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    fun init() {
        LayoutInflater.from(context).inflate(R.layout.view_metronome, this, true)

        ivPlus.setOnClickListener { presenter.addPeriod() }
        ivMinus.setOnClickListener { presenter.subPeriod() }
        bAction.setOnClickListener { presenter.actionClick() }
        metronomeBackground.setOnClickListener { listener?.onMetronomeBackgroundClick() }
        actionLayout.setOnClickListener { }

        mediaPlayer = MediaPlayer.create(context, R.raw.wood)
        tickHandler = Handler(Looper.getMainLooper())

        presenter.subscribe()
    }

    override fun showPeriod(period: String) {
        tvPeriod.text = period
    }

    override fun showStartActionButton() {
        bAction.setText(R.string.start)
        bAction.background.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), PorterDuff.Mode.MULTIPLY)
    }

    override fun showStopActionButton() {
        bAction.setText(R.string.stop)
        bAction.background.setColorFilter(ContextCompat.getColor(context, R.color.colorAccent), PorterDuff.Mode.MULTIPLY)
    }

    override fun startTick(period: Long) {

        if (tickRunnable != null)
            tickHandler.removeCallbacks(tickRunnable)

        tickRunnable = Runnable {
            mediaPlayer.start()
            tickHandler.postDelayed(tickRunnable, 60000 / period)
        }

        tickHandler.postDelayed(tickRunnable, 60000 / period)

        listener?.metronomeStatusChange(true)
    }

    override fun stopTick() {
        tickHandler.removeCallbacks(tickRunnable)
        mediaPlayer.stop()

        mediaPlayer = MediaPlayer.create(context, R.raw.wood)
        listener?.metronomeStatusChange(false)
    }


    fun setOnMetronomeStatusChange(callback: OnMetronomeStatusChange) {
        listener = callback
    }


    interface OnMetronomeStatusChange {

        fun metronomeStatusChange(enable: Boolean)

        fun onMetronomeBackgroundClick()
    }
}