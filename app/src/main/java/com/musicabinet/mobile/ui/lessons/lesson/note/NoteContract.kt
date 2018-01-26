package com.musicabinet.mobile.ui.lessons.lesson.note

import com.musicabinet.mobile.model.lesson.machine.ToneOrChordResult
import com.musicabinet.mobile.model.lesson.machine.note.NoteItem

/**
 * @author Kirchhoff-
 */
interface NoteContract {

    interface View {

        fun showLoading(show: Boolean)

        fun showError()

        fun showModule(list: List<NoteItem>)

        fun showInstrument(list: List<NoteItem>)

    }

    interface Presenter {

        fun subscribe(toneOrChordResult: ToneOrChordResult)

        fun unsubscribe()

    }
}