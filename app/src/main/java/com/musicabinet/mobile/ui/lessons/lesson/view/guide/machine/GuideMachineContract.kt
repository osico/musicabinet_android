package com.musicabinet.mobile.ui.lessons.lesson.view.guide.machine

import com.musicabinet.mobile.model.lesson.remote.Stave

/**
 * @author Kirchhoff-
 */
interface GuideMachineContract {

    interface View {

        fun addRow(row: Int)

        fun showError()

        fun showLoading(show: Boolean)

        fun showImprovisationNote()

    }

    interface Presenter {

        fun subscribe(stave: Stave?)

        fun onElementSelected(rowString: String)

    }
}