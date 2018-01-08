package com.musicabinet.mobile.ui.lessons.lesson.view.sound

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
    }

    interface Presenter {

        fun setAccompanimentsData(accompaniments: Set<Accompaniment>)

        fun showAccompaniment(position: Int)

        fun play()

        fun getSoundsId(): List<String>
    }
}