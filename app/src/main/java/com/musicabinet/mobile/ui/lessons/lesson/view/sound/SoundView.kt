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

    private lateinit var accompanimentsList: ArrayList<Accompaniment>
    private lateinit var presenter: SoundViewContract.Presenter

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

        cDrums.setOnClickListener { cDrums.isChecked = !cDrums.isChecked }
        cBass.setOnClickListener { cBass.isChecked = !cBass.isChecked }
        cKeys.setOnClickListener { cKeys.isChecked = !cKeys.isChecked }

        presenter = SoundViewPresenter(this, Injection.provideRepository(),
                context.filesDir)

        ivPlay.setOnClickListener { presenter.play() }
    }


    fun setAccompaniments(accompaniments: Set<Accompaniment>) {

        presenter.setAccompanimentsData(accompaniments)
    }


    override fun showAccompaniment(accompaniment: Accompaniment) {
        cDrums.isChecked = accompaniment.drums != null && accompaniment.drums.dataAvailable
        cBass.isChecked = accompaniment.bass != null && accompaniment.bass.dataAvailable
        cKeys.isChecked = accompaniment.keys != null && accompaniment.keys.dataAvailable

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
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        presenter.showAccompaniment(position)
    }

    override fun setAudioFiles(list: List<String>) {

        for (item in list) {
            val mediaPlayer = MediaPlayer.create(context,
                    Uri.parse(File(context.filesDir, item).absolutePath))
            mediaPlayer.play()
        }
    }
}