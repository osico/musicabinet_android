package com.musicabinet.mobile.ui.lessons.lesson.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.musicabinet.mobile.R
import com.musicabinet.mobile.model.lesson.Lesson

/**
 * @author Kirchhoff-
 */
class LessonSelectDialog : Dialog {

    private val lessonList: List<Lesson>
    private val lessonSelectCallback: OnLessonSelectListener

    public constructor(context: Context, lessons: List<Lesson>,
                       callback: OnLessonSelectListener) : super(context) {
        this.lessonList = lessons
        this.lessonSelectCallback = callback
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_lesson_select)
    }


    public interface OnLessonSelectListener {

        fun onLessonSelect(id: String)
    }
}