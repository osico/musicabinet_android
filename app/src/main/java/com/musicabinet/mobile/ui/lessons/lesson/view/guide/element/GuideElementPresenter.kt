package com.musicabinet.mobile.ui.lessons.lesson.view.guide.element

import com.musicabinet.mobile.Constants
import com.musicabinet.mobile.model.lesson.machine.FileDataItem
import com.musicabinet.mobile.model.lesson.machine.ImprovisationResultItem
import com.musicabinet.mobile.model.lesson.machine.ToneOrChord
import com.musicabinet.mobile.model.lesson.machine.ToneOrChordResult
import com.musicabinet.mobile.model.lesson.machine.diagram.DiagramImageResponse
import com.musicabinet.mobile.model.lesson.machine.note.image.NoteElement
import com.musicabinet.mobile.repository.MusicabinetRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable


/**
 * @author Kirchhoff-
 */
class GuideElementPresenter(private val view: GuideElementContract.View,
                            private val repository: MusicabinetRepository) : GuideElementContract.Presenter {

    private val subscriptions = CompositeDisposable()

    private var toneOrChordResult: ToneOrChordResult? = null
    private var noteElement: NoteElement? = null
    private var toneCode: String? = null
    private var chordCode: String? = null
    private var moduleId: String? = null
    private var courseId: String? = null
    private var fileDataItem: FileDataItem? = null
    private var isUserReselecting = false

    override fun subscribe() {
        view.enableFabClick(true)
        view.enableNoteClick(false)
        view.showLoading(false)
    }

    override fun requestToneAndChord() {
        view.requestToneAndChord(Constants.GUIDE_MACHINE_REQUEST_CODE,
                toneOrChordResult, toneCode, chordCode, Constants.GUIDE_MACHINE_TAG_RESULT_ARG)
    }

    override fun showToneAndChord(result: ToneOrChordResult) {
        toneOrChordResult = result
        view.enableNoteClick(true)
        view.enableFabClick(false)
        view.showAddButton(false)
        view.showToneAndChord(true)
        view.setTone(result.tone.name)
        view.setChord(result.chord.name)
        noteElement = null
        view.showDefaultNoteImage()
        isUserReselecting = true
    }

    override fun setFileDataItem(item: FileDataItem) {
        this.fileDataItem = item
        if (item.chord != null && item.tone != null) {
            view.enableNoteClick(true)
            view.enableFabClick(false)
            view.showAddButton(false)
            view.showToneAndChord(true)
            view.setTone(item.tone!!)
            view.setChord(item.chord!!)
            toneCode = item.tone
            chordCode = item.chord
            val toneElement = ToneOrChord("", item.tone!!, "", 0)
            val chordElement = ToneOrChord("", item.chord!!, "", 0)
            toneOrChordResult = ToneOrChordResult(toneElement, chordElement)
        } else if (item.noteInformation != null) {
            view.showLoading(true)
            getDiagramImage(item.noteInformation!!)
        }
    }

    override fun requestNote() {
        if (toneOrChordResult != null)
            view.requestNote(toneOrChordResult!!, noteElement, moduleId, courseId)
    }

    override fun showNote(element: NoteElement, moduleId: String?, courseId: String?) {
        noteElement = element
        this.moduleId = moduleId
        this.courseId = courseId
        view.showNoteImage(element.image.id)
        isUserReselecting = true
    }

    override fun getImprovisationInformation() = ImprovisationResultItem(toneOrChordResult,
            noteElement, fileDataItem, isUserReselecting)

    private fun getDiagramImage(noteInformation: String) {
        subscriptions.add(repository.getDiagramImage(noteInformation)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ diagramResponse: DiagramImageResponse ->
                    val diagramElement = diagramResponse.elements.get(noteInformation)
                    view.showLoading(false)
                    if (diagramElement != null) {
                        view.enableNoteClick(true)
                        view.enableFabClick(false)
                        view.showAddButton(false)
                        view.showToneAndChord(true)
                        view.setTone(diagramElement.diagramToneName)
                        view.setChord(diagramElement.diagramChordTypeName)
                        view.showNoteImage(diagramElement.image.id)

                        val tone = ToneOrChord(diagramElement.diagramToneId,
                                diagramElement.diagramToneName, "", 0)
                        val chord = ToneOrChord(diagramElement.diagramChordTypeId,
                                diagramElement.diagramChordTypeName, "", 1)

                        toneOrChordResult = ToneOrChordResult(tone, chord)
                    }
                }, { t: Throwable? ->
                    view.showLoading(false)
                }))
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }
}