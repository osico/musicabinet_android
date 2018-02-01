package com.musicabinet.mobile.ui.lessons.lesson.view.guide.machine

import com.musicabinet.mobile.model.lesson.remote.Stave

/**
 * @author Kirchhoff-
 */
interface GuideMachineContract {

    interface View {

        fun addRow(row: Int)

    }

    interface Presenter {

        fun subscribe(stave: Stave?)

        fun onElementSelected(rowString: String)

    }
}