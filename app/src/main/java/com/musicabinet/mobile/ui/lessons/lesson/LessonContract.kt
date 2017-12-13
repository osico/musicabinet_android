package com.musicabinet.mobile.ui.lessons.lesson

import android.content.Intent
import com.musicabinet.mobile.model.lesson.Lesson

/**
 * @author Kirchhoff-
 */
interface LessonContract {

    interface View {

        fun showSuccess()

        fun showError()

        fun showSelectLesson(lessonList: List<Lesson>, requestCode: Int,
                             resultId: String, resultName: String)

        fun onLessonSelected()
    }

    interface Presenter {

        fun getLessonGroup(id: String)

        fun selectLessonClick()

        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    }
}