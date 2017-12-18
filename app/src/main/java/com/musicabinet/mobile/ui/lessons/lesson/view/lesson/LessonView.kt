package com.musicabinet.mobile.ui.lessons.lesson.view.lesson

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.loadLessonImage
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


    public fun setLessonImages(lessonImages: List<List<String>>) {
        for (image in lessonImages[0]) {
            ivLesson1.loadLessonImage(image)
            //ivLesson2.loadLessonImage(lessonImages[0].[0])
            //ivLesson3.loadLessonImage(lessonImages[2])
            // ivLesson4.loadLessonImage(lessonImages[3])
        }
    }
}