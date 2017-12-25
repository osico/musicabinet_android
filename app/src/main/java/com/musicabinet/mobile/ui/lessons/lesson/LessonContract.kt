package com.musicabinet.mobile.ui.lessons.lesson

import android.content.Intent
import com.musicabinet.mobile.model.lesson.lesson.Lesson
import com.musicabinet.mobile.model.lesson.local.LessonData
import com.musicabinet.mobile.model.lesson.local.MethodItem

/**
 * @author Kirchhoff-
 */
interface LessonContract {

    interface View {

        fun showSuccess()

        fun showError()

        fun showLoading(show: Boolean)

        fun showLessonTitle(title: String)

        fun showSelectLesson(lessonList: List<Lesson>, lessonId: String, requestCode: Int,
                             resultId: String, resultName: String)

        fun setLessonTime(time: Long, lessonId: String)

        fun showMethod(methodList: List<MethodItem>)

        fun showLessonImages(lessonImagesList: List<LessonData>)

        fun onLessonSelected(id: String)
    }

    interface Presenter {

        fun getLessonGroup(id: String)

        fun getLessonInformation(id: String)

        fun getPreparedLesson(id: String)

        fun selectLessonClick()

        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    }
}