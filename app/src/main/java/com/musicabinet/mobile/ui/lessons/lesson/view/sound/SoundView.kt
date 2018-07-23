package com.musicabinet.mobile.ui.lessons.lesson.view.sound

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.musicabinet.mobile.Injection
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.configVisibility
import com.musicabinet.mobile.extensions.setVisible
import com.musicabinet.mobile.model.lesson.remote.Accompaniment
import com.musicabinet.mobile.ui.lessons.lesson.tonechord.ToneAndChordActivity
import kotlinx.android.synthetic.main.view_sound.view.*
import java.io.File
import java.util.*


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

        presenter = SoundViewPresenter(this, Injection.provideRepository(), Injection.provideStorage(),
                context.filesDir)

        ivPlay.setOnClickListener { presenter.play() }
        tvSelectFromLibrary.setOnClickListener { presenter.showAccompaniment(0) }
    }


    override fun setAccompaniments(accompaniments: MutableSet<Accompaniment>) {
        //Added last item to Accompaniment for selecting item from library

        val selectFromLibrary = Accompaniment("0",
                resources.getString(R.string.sound_select_from_library), null, null, null)
        accompaniments.add(selectFromLibrary)
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

    override fun restoreSelectedPosition(position: Int) {
        sRoad.onItemSelectedListener = null
        sRoad.setSelection(position)
        sRoad.onItemSelectedListener = this
    }

    override fun setElementVisibility(visible: Boolean) {
        setVisible(visible)
    }

    override fun setAccompanimentList(accompaniments: List<Accompaniment>) {
        val adapter = SoundViewAdapter(context, R.layout.item_sound_spinner, accompaniments)
        sRoad.adapter = adapter

        if (accompaniments.size == 1) {
            tvSelectFromLibrary.setVisible(true)
            sRoad.isEnabled = false
            sRoad.setVisible(false)
        } else {
            tvSelectFromLibrary.setVisible(false)
            sRoad.isEnabled = true
            sRoad.setVisible(true)
        }

        sRoad.onItemSelectedListener = this
    }

    override fun showLoading(show: Boolean) {
        progressBar.setVisible(show)
        ivPlay.setVisible(!show)
        cDrums.isClickable = !show
        cBass.isClickable = !show
        cKeys.isClickable = !show
    }

    override fun showError() {
        Toast.makeText(context, R.string.error_during_loading_accompaniment, Toast.LENGTH_LONG).show()
    }

    override fun requestToneAndChord(requestCode: Int) {
        ToneAndChordActivity.requestToneAndChord(context as Activity, requestCode, null, null, null, "")
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
            val media = MediaPlayer.create(context,
                    Uri.parse(File(context.filesDir, list[i]).absolutePath))
            if (media != null) {
                musicPlayerList.add(media)
            } else {
                if (i == 2) {
                    cKeys.isChecked = false
                    cKeys.isEnabled = false
                }

                if (i == 1) {
                    cKeys.isChecked = false
                    cKeys.isEnabled = false
                    cBass.isChecked = false
                    cBass.isEnabled = false
                }

                if (i == 0) {
                    cKeys.isChecked = false
                    cKeys.isEnabled = false
                    cBass.isChecked = false
                    cBass.isEnabled = false
                    cDrums.isChecked = false
                    cDrums.isEnabled = false
                    ivPlay.isEnabled = false
                }
            }
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
            if (presenter.getSoundsId()[i] != null) {
                val file = File(context.filesDir, presenter.getSoundsId()[i])

                val mediaPlayer: MediaPlayer? = MediaPlayer.create(context,
                        Uri.parse(file.absolutePath))
                if (mediaPlayer != null) {
                    musicPlayerList.add(MediaPlayer.create(context,
                            Uri.parse(file.absolutePath)))

                    //If accompaniment - don't check set sound to 0
                    if (i == 0 && !cDrums.isChecked)
                        musicPlayerList[0].setVolume(0f, 0f)
                    if (i == 1 && !cBass.isChecked)
                        musicPlayerList[1].setVolume(0f, 0f)
                    if (i == 2 && !cKeys.isChecked)
                        musicPlayerList[2].setVolume(0f, 0f)

                    //Start play all accompaniments
                    //  musicPlayerList[i].isLooping = true
                    musicPlayerList[i].start()
                    //  musicPlayerList[i].isLooping = false
                    musicPlayerList[i].setOnCompletionListener {
                        musicPlayerList[i].seekTo(0)
                        musicPlayerList[i].start()
                    }
                }
            }
        }

        ivPlay.setImageResource(R.drawable.ic_button_stop)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        presenter.onActivityResult(requestCode, resultCode, data)
    }

}