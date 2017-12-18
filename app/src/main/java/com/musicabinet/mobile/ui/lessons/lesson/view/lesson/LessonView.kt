package com.musicabinet.mobile.ui.lessons.lesson.view.lesson

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.loadLessonImage
import com.musicabinet.mobile.extensions.setVisible
import kotlinx.android.synthetic.main.view_lesson.view.*

/**
 * @author Kirchhoff-
 */
class LessonView : FrameLayout {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        LayoutInflater.from(context).inflate(R.layout.view_lesson, this, true)
    }


    fun setLessonImages(lessonImages: List<List<String>>) {

        if (lessonImages[0].isNotEmpty()) {
            ivLesson1.setVisible(true)
            ivLesson1.loadLessonImage(lessonImages[0][0])
        } else {
            ivLesson1.setVisible(false)
        }

        if (lessonImages[0].size > 1) {
            ivLesson2.setVisible(true)
            ivLesson2.loadLessonImage(lessonImages[0][1])
        } else {
            ivLesson2.setVisible(false)
        }

        if (lessonImages[0].size > 2) {
            ivLesson3.setVisible(true)
            ivLesson3.loadLessonImage(lessonImages[0][2])
        } else {
            ivLesson3.setVisible(false)
        }

        if (lessonImages[0].size > 3) {
            ivLesson4.setVisible(true)
            ivLesson4.loadLessonImage(lessonImages[0][3])
        } else {
            ivLesson4.setVisible(false)
        }
    }
}