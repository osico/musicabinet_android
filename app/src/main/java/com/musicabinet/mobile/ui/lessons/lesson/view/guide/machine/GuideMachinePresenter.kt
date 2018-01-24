package com.musicabinet.mobile.ui.lessons.lesson.view.guide.machine

/**
 * @author Kirchhoff-
 */
class GuideMachinePresenter(private val view: GuideMachineContract.View) : GuideMachineContract.Presenter {

    private var row = 0

    override fun subscribe() {
        view.addRow(0)
    }
}