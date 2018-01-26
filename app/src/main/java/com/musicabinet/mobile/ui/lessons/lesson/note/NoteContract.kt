package com.musicabinet.mobile.ui.lessons.lesson.note

import com.musicabinet.mobile.model.lesson.machine.ToneOrChordResult
import com.musicabinet.mobile.model.lesson.machine.note.NoteItem
import com.musicabinet.mobile.model.lesson.machine.note.image.NoteElement

/**
 * @author Kirchhoff-
 */
interface NoteContract {

    interface View {

        fun showLoading(show: Boolean)

        fun showError()

        fun showModule(list: List<NoteItem>)

        fun showInstrument(list: List<NoteItem>)

        fun showNoteImage(list: List<NoteElement>)

    }

    interface Presenter {

        fun subscribe(toneOrChordResult: ToneOrChordResult)

        fun unsubscribe()

    }
}