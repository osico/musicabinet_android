package com.musicabinet.mobile.ui.lessons.lesson.view.guide.machine

import android.util.Log
import com.musicabinet.mobile.model.lesson.remote.Stave

/**
 * @author Kirchhoff-
 */
class GuideMachinePresenter(private val view: GuideMachineContract.View) : GuideMachineContract.Presenter {

    private var row = 0
    private var firstSelect = true

    override fun subscribe(stave: Stave?) {
        if (stave == null)
            view.addRow(row)
        else
            Log.d("TAG", "Should request and parse file")
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