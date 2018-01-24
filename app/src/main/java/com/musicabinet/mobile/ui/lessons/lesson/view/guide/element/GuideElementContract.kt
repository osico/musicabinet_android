package com.musicabinet.mobile.ui.lessons.lesson.view.guide.element

/**
 * @author Kirchhoff-
 */
interface GuideElementContract {

    interface View {

        fun enableFabClick(enable: Boolean)

        fun enableNoteClick(enable: Boolean)

        fun requestToneAndChord(requestCode: Int, toneArg: String, chordArg: String)

    }

    interface Presenter {

        fun subscribe()

        fun requestToneAndChord()

    }
}