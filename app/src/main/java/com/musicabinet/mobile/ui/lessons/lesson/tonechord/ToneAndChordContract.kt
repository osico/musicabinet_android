package com.musicabinet.mobile.ui.lessons.lesson.tonechord

import com.musicabinet.mobile.model.lesson.machine.ToneOrChord

/**
 * @author Kirchhoff-
 */
interface ToneAndChordContract {

    interface View {

        fun showLoading(show: Boolean)

        fun showError()

        fun showTone(list: List<ToneOrChord>)

        fun showChord(list: List<ToneOrChord>)
    }

    interface Presenter {

        fun subscribe()

        fun unsubscribe()
    }
}