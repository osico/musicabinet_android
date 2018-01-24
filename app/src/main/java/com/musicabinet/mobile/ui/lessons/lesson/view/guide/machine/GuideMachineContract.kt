package com.musicabinet.mobile.ui.lessons.lesson.view.guide.machine

/**
 * @author Kirchhoff-
 */
interface GuideMachineContract {

    interface View {

        fun addRow(row: Int)

    }

    interface Presenter {

        fun subscribe()

    }
}