package com.musicabinet.mobile.ui.lesson.list

/**
 * @author Kirchhoff-
 */
interface LessonListContract {

    interface View {

        fun showLoading(visible: Boolean)

        fun showSuccess()

        fun showError()
    }

    interface Presenter {

        fun getFilters(instrumentId: String)

    }
}