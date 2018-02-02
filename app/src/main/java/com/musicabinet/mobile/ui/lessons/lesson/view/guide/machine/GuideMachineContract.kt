package com.musicabinet.mobile.ui.lessons.lesson.view.guide.machine

import com.musicabinet.mobile.model.lesson.machine.FileDataItem
import com.musicabinet.mobile.model.lesson.remote.Stave

/**
 * @author Kirchhoff-
 */
interface GuideMachineContract {

    interface View {

        fun addRow(row: Int)

        fun showError()

        fun showLoading(show: Boolean)

        fun showImprovisationNote(list: List<FileDataItem>)

    }

    interface Presenter {

        fun subscribe(stave: Stave?)

        fun addedRows(rowValue: Int)

        fun onElementSelected(rowString: String)

    }
}