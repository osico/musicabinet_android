package com.musicabinet.mobile.ui.lessons.lesson.view.guide.element

import com.musicabinet.mobile.Constants
import com.musicabinet.mobile.model.lesson.machine.FileDataItem
import com.musicabinet.mobile.model.lesson.machine.ToneOrChordResult
import com.musicabinet.mobile.model.lesson.machine.note.image.NoteElement


/**
 * @author Kirchhoff-
 */
class GuideElementPresenter(private val view: GuideElementContract.View) : GuideElementContract.Presenter {

    private var toneOrChordResult: ToneOrChordResult? = null
    private var noteElement: NoteElement? = null

    override fun subscribe() {
        view.enableFabClick(true)
        view.enableNoteClick(false)
        view.showLoading(false)
    }

    override fun requestToneAndChord() {
        view.requestToneAndChord(Constants.GUIDE_MACHINE_REQUEST_CODE,
                Constants.GUIDE_MACHINE_TAG_RESULT_ARG)
    }

    override fun showToneAndChord(result: ToneOrChordResult) {
        toneOrChordResult = result
        view.enableNoteClick(true)
        view.enableFabClick(false)
        view.showAddButton(false)
        view.showToneAndChord(true)
        view.setTone(result.tone.name)
        view.setChord(result.chord.name)
    }

    override fun setFileDataItem(item: FileDataItem) {
        if (item.chord != null && item.tone != null) {
            view.enableNoteClick(true)
            view.enableFabClick(false)
            view.showAddButton(false)
            view.showToneAndChord(true)
            view.setTone(item.tone!!)
            view.setChord(item.chord!!)
        } else if (item.noteInformation != null) {
            view.showLoading(true)
        }
    }

    override fun requestNote() {
        if (toneOrChordResult != null)
            view.requestNote(toneOrChordResult!!, noteElement)
    }

    override fun showNote(element: NoteElement) {
        noteElement = element
        view.showNoteImage(element.image.id)
    }
}