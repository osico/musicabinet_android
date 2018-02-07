package com.musicabinet.mobile.ui.lessons.lesson.view.sound

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import com.musicabinet.mobile.Injection
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.configVisibility
import com.musicabinet.mobile.extensions.play
import com.musicabinet.mobile.extensions.setVisible
import com.musicabinet.mobile.model.lesson.remote.Accompaniment
import kotlinx.android.synthetic.main.view_sound.view.*
import java.io.File


/**
 * @author Kirchhoff-
 */
class SoundView : ConstraintLayout, AdapterView.OnItemSelectedListener, SoundViewContract.View {

    private lateinit var presenter: SoundViewContract.Presenter
    private var musicPlayerList: MutableList<MediaPlayer> = java.util.ArrayList()
    private var check: Int = 0

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
        LayoutInflater.from(context).inflate(R.layout.view_sound, this, true)

        cDrums.setOnClickListener {
            cDrums.isChecked = !cDrums.isChecked
            if (cDrums.isChecked)
                musicPlayerList[0].setVolume(1f, 1f)
            else
                musicPlayerList[0].setVolume(0f, 0f)

        }
        cBass.setOnClickListener {
            cBass.isChecked = !cBass.isChecked
            if (cBass.isChecked)
                musicPlayerList[1].setVolume(1f, 1f)
            else
                musicPlayerList[1].setVolume(0f, 0f)
        }
        cKeys.setOnClickListener {
            cKeys.isChecked = !cKeys.isChecked
            if (cKeys.isChecked)
                musicPlayerList[2].setVolume(1f, 1f)
            else
                musicPlayerList[2].setVolume(0f, 0f)
        }

        presenter = SoundViewPresenter(this, Injection.provideRepository(),
                context.filesDir)

        ivPlay.setOnClickListener { presenter.play() }
    }


    fun setAccompaniments(accompaniments: Set<Accompaniment>) {

        presenter.setAccompanimentsData(accompaniments)
    }

    fun onPause() {
        for (item in musicPlayerList)
            item.stop()

        ivPlay.setImageResource(R.drawable.ic_button_play)
        presenter.stop()
        presenter.unsubscribe()
    }


    override fun showAccompaniment(accompaniment: Accompaniment) {
        cDrums.isChecked = accompaniment.drums != null
        cBass.isChecked = accompaniment.bass != null
        cKeys.isChecked = accompaniment.keys != null

        cDrums.configVisibility()
        cBass.configVisibility()
        cKeys.configVisibility()
    }

    override fun setElementVisibility(visible: Boolean) {
        setVisible(visible)
    }

    override fun setAccompanimentList(accompaniments: List<Accompaniment>) {
        val adapter = SoundViewAdapter(context, R.layout.item_sound_spinner, accompaniments)
        sRoad.adapter = adapter

        sRoad.onItemSelectedListener = this
    }

    override fun showLoading(show: Boolean) {
        progressBar.setVisible(show)
        ivPlay.setVisible(!show)
        cDrums.isClickable = !show
        cBass.isClickable = !show
        cKeys.isClickable = !show
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        if (++check > 1) {
            presenter.showAccompaniment(position)
        }
    }

    override fun setAudioFiles(list: List<String>) {

        musicPlayerList = ArrayList()
        for (i in list.indices) {
            musicPlayerList.add(MediaPlayer.create(context,
                    Uri.parse(File(context.filesDir, list[i]).absolutePath)))
        }
    }

    override fun stopPlay() {
        for (item in musicPlayerList)
            item.stop()

        ivPlay.setImageResource(R.drawable.ic_button_play)
    }

    override fun startPlay() {
        musicPlayerList.clear()
        for (i in presenter.getSoundsId().indices) {
            musicPlayerList.add(MediaPlayer.create(context,
                    Uri.parse(File(context.filesDir, presenter.getSoundsId()[i]).absolutePath)))

            //If accompaniment - don't check set sound to 0
            if (i == 0 && !cDrums.isChecked)
                musicPlayerList[0].setVolume(0f, 0f)
            if (i == 1 && !cBass.isChecked)
                musicPlayerList[1].setVolume(0f, 0f)
            if (i == 2 && !cKeys.isChecked)
                musicPlayerList[2].setVolume(0f, 0f)

            //Start play all accompaniments
            musicPlayerList[i].play()
        }



        ivPlay.setImageResource(R.drawable.ic_button_stop)
    }

}