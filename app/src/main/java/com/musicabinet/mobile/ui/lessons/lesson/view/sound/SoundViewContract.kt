package com.musicabinet.mobile.ui.lessons.lesson.view.sound

import android.content.Intent
import com.musicabinet.mobile.model.lesson.remote.Accompaniment

/**
 * @author Kirchhoff-
 */
interface SoundViewContract {

    interface View {

        fun setElementVisibility(visible: Boolean)

        fun setAccompanimentList(accompaniments: List<Accompaniment>)

        fun showAccompaniment(accompaniment: Accompaniment)

        fun showLoading(show: Boolean)

        fun setAudioFiles(list: List<String>)

        fun stopPlay()

        fun startPlay()

        fun requestToneAndChord(requestCode: Int)

        fun restoreSelectedPosition(position: Int)
    }

    interface Presenter {

        fun setAccompanimentsData(accompaniments: Set<Accompaniment>)

        fun showAccompaniment(position: Int)

        fun play()

        fun stop()

        fun getSoundsId(): List<String?>

        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)

        fun unsubscribe()
    }
}