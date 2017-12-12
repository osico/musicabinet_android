package com.musicabinet.mobile.ui.lessons.lesson

/**
 * @author Kirchhoff-
 */
interface LessonContract {

    interface View {

        fun showSuccess()

        fun showError()
    }

    interface Presenter {

        fun getLessonGroup(id: String)
    }
}