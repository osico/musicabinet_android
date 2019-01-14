package com.musicabinet.mobile.ui.view.metronome

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Handler
import android.os.Looper
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.setBackgroundTint
import kotlinx.android.synthetic.main.view_metronome.view.*

/**
 * @author Kirchhoff-
 */
class MetronomeView : ConstraintLayout, MetronomeContract.View, SoundPool.OnLoadCompleteListener {

    private val presenter = MetronomePresenter(this)

    private lateinit var soundPool: SoundPool
    private var tickRunnable: Runnable? = null
    private lateinit var tickHandler: Handler
    private var listener: OnMetronomeStatusChange? = null
    private var soundId: Int? = null

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

        if(android.os.Build.VERSION.SDK_INT >= 21) {
            val soundPoolBuilder = SoundPool.Builder()
            soundPoolBuilder.setMaxStreams(1)
            soundPoolBuilder.setAudioAttributes(
                    AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .build()
            )
            soundPool = soundPoolBuilder.build()
        } else {
            soundPool = SoundPool(1, AudioManager.STREAM_MUSIC, 0)
        }
        soundPool.setOnLoadCompleteListener(this)
        soundId = soundPool.load(context, R.raw.wood, 1)

        tickHandler = Handler(Looper.getMainLooper())

        presenter.subscribe()
    }

    override fun showPeriod(period: String) {
        tvPeriod.text = period
    }

    override fun showStartActionButton() {
        bAction.setText(R.string.start)
        bAction.setBackgroundTint(R.color.colorPrimary)
    }

    override fun showStopActionButton() {
        bAction.setText(R.string.stop)
        bAction.setBackgroundTint(R.color.colorAccent)
    }

    override fun startTick(period: Long) {

        if (tickRunnable != null)
            tickHandler.removeCallbacks(tickRunnable)

        tickRunnable = Runnable {
            soundId?.let {
                soundPool.play(it, 1f, 1f, 0, 0, 1f)
            }
            tickHandler.postDelayed(tickRunnable, 60000 / period)
        }

        tickHandler.postDelayed(tickRunnable, 60000 / period)

        listener?.metronomeStatusChange(true)
    }

    override fun stopTick() {
        tickHandler.removeCallbacks(tickRunnable)
        soundId?.let {
            soundPool.stop(it)
        }

        listener?.metronomeStatusChange(false)
    }

    override fun onLoadComplete(soundPool: SoundPool?, sampleId: Int, status: Int) {

    }

    fun setOnMetronomeStatusChange(callback: OnMetronomeStatusChange) {
        listener = callback
    }




    interface OnMetronomeStatusChange {

        fun metronomeStatusChange(enable: Boolean)

        fun onMetronomeBackgroundClick()
    }
}