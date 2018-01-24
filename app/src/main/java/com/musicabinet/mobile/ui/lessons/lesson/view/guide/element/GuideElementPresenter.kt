package com.musicabinet.mobile.ui.lessons.lesson.view.guide.element

import com.musicabinet.mobile.Constants


/**
 * @author Kirchhoff-
 */
class GuideElementPresenter(private val view: GuideElementContract.View) : GuideElementContract.Presenter {

    override fun subscribe() {
        view.enableFabClick(true)
        view.enableNoteClick(false)
    }

    override fun requestToneAndChord() {
        view.requestToneAndChord(Constants.GUIDE_MACHINE_REQUEST_CODE,
                Constants.GUIDE_MACHINE_TAG_RESULT_ARG)
    }

}