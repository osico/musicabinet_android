package com.musicabinet.mobile.ui.lessons.lesson.view.guide.element

import com.musicabinet.mobile.model.lesson.machine.FileDataItem
import com.musicabinet.mobile.model.lesson.machine.ToneOrChordResult
import com.musicabinet.mobile.model.lesson.machine.note.image.NoteElement

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

        fun showNoteImage(url: String)

        fun setTone(tone: String)

        fun setChord(chord: String)

        fun requestNote(toneAndChordResult: ToneOrChordResult, noteElement: NoteElement?)

    }

    interface Presenter {

        fun subscribe()

        fun requestToneAndChord()

        fun showToneAndChord(result: ToneOrChordResult)

        fun showNote(element: NoteElement)

        fun setFileDataItem(item: FileDataItem)

        fun requestNote()
    }
}