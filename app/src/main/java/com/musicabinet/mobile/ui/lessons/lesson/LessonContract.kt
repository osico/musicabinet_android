package com.musicabinet.mobile.ui.lessons.lesson

import com.musicabinet.mobile.model.lesson.Lesson

/**
 * @author Kirchhoff-
 */
interface LessonContract {

    interface View {

        fun showSuccess()

        fun showError()

        fun showLessonsDialog(lessonList: List<Lesson>)
    }

    interface Presenter {

        fun getLessonGroup(id: String)

        fun selectLessonClick()
    }
}