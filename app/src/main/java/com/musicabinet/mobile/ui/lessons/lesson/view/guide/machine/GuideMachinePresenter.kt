package com.musicabinet.mobile.ui.lessons.lesson.view.guide.machine

/**
 * @author Kirchhoff-
 */
class GuideMachinePresenter(private val view: GuideMachineContract.View) : GuideMachineContract.Presenter {

    private var row = 0
    private var firstSelect = true

    override fun subscribe() {
        view.addRow(row)
    }

    override fun onElementSelected(rowString: String) {

        if (firstSelect) {
            firstSelect = false
            row += 1
            view.addRow(row)
            return
        }

        val rowInt = rowString.toInt()
        if (rowInt == row) {
            row++
            view.addRow(row)
        }
    }
}