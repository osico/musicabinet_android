package com.musicabinet.mobile.ui.lessons.lesson.dialog

import com.musicabinet.mobile.model.lesson.lesson.Lesson

/**
 * @author Kirchhoff-
 */
class LessonSelectPresenter(private val view: LessonSelectContract.View) : LessonSelectContract.Presenter {

    override fun selectLesson(lesson: Lesson) {
        view.onLessonSelect(lesson.id, lesson.name)
    }

}