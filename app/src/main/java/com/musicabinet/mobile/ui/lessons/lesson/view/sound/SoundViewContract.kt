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
    }

    interface Presenter {

        fun setAccompanimentsData(accompaniments: Set<Accompaniment>)

        fun showAccompaniment(position: Int)
    }
}