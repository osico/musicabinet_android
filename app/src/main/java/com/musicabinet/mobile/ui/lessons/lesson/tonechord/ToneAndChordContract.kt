package com.musicabinet.mobile.ui.lessons.lesson.tonechord

import com.musicabinet.mobile.model.lesson.machine.Chord
import com.musicabinet.mobile.model.lesson.machine.Tone

/**
 * @author Kirchhoff-
 */
interface ToneAndChordContract {

    interface View {

        fun showLoading(show: Boolean)

        fun showError()

        fun showTone(list: List<Tone>)

        fun showChord(list: List<Chord>)
    }

    interface Presenter {

        fun subscribe()

        fun unsubscribe()
    }
}