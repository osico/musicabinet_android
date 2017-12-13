package com.musicabinet.mobile.ui.lessons.lesson.dialog

import com.musicabinet.mobile.model.lesson.Lesson

/**
 * @author Kirchhoff-
 */
interface LessonSelectContract {

    interface View {

        fun onLessonSelect(id: String, name: String)

    }

    interface Presenter {

        fun selectLesson(lesson: Lesson)

    }
}