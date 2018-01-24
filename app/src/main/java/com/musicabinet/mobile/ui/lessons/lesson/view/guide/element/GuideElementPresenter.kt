package com.musicabinet.mobile.ui.lessons.lesson.view.guide.element

import com.musicabinet.mobile.Constants.CHORD_RESULT_ARG
import com.musicabinet.mobile.Constants.TAG_RESULT_ARG
import com.musicabinet.mobile.Constants.TONE_AND_CHORD_REQUEST_CODE
import com.musicabinet.mobile.Constants.TONE_RESULT_ARG

/**
 * @author Kirchhoff-
 */
class GuideElementPresenter(private val view: GuideElementContract.View) : GuideElementContract.Presenter {

    override fun subscribe() {
        view.enableFabClick(true)
        view.enableNoteClick(false)
    }

    override fun requestToneAndChord() {
        view.requestToneAndChord(TONE_AND_CHORD_REQUEST_CODE, TAG_RESULT_ARG,
                TONE_RESULT_ARG, CHORD_RESULT_ARG)
    }

}