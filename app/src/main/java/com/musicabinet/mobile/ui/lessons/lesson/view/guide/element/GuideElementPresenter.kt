package com.musicabinet.mobile.ui.lessons.lesson.view.guide.element

/**
 * @author Kirchhoff-
 */
class GuideElementPresenter(private val view: GuideElementContract.View) : GuideElementContract.Presenter {

    companion object {
        const val TONE_AND_CHORD_REQUEST_CODE = 12000
        const val TONE_RESULT_ARG = "TONE_RESULT_ARG"
        const val CHORD_RESULT_ARG = "CHORD_RESULT_ARG"
    }

    override fun subscribe() {
        view.enableFabClick(true)
        view.enableNoteClick(false)
    }

    override fun requestToneAndChord() {
        view.requestToneAndChord(TONE_AND_CHORD_REQUEST_CODE, TONE_RESULT_ARG, CHORD_RESULT_ARG)
    }

}