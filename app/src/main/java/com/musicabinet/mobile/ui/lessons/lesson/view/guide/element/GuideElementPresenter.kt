package com.musicabinet.mobile.ui.lessons.lesson.view.guide.element

/**
 * @author Kirchhoff-
 */
class GuideElementPresenter(private val view: GuideElementContract.View) : GuideElementContract.Presenter {

    override fun subscribe() {
        view.enableFabClick(true)
        view.enableNoteClick(false)
    }

    override fun onFabClick() {
        view.requestToneAndChord()
    }

}