package com.musicabinet.mobile.ui.lessons.lesson.view.guide.element

import com.musicabinet.mobile.model.lesson.machine.ToneOrChordResult

/**
 * @author Kirchhoff-
 */
interface GuideElementContract {

    interface View {

        fun enableFabClick(enable: Boolean)

        fun enableNoteClick(enable: Boolean)

        fun requestToneAndChord(requestCode: Int, tagArg: String)

        fun showAddButton(show: Boolean)

        fun showToneAndChord(show: Boolean)

        fun setTone(tone: String)

        fun setChord(chord: String)

        fun requestNote(toneAndChordResult: ToneOrChordResult)

    }

    interface Presenter {

        fun subscribe()

        fun requestToneAndChord()

        fun showToneAndChord(result: ToneOrChordResult)

        fun requestNote()
    }
}