package com.musicabinet.mobile.ui.lesson.list

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.musicabinet.mobile.R

/**
 * @author Kirchhoff-
 */
class LessonListView : FrameLayout {
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
        LayoutInflater.from(context).inflate(R.layout.view_lesson_list, this, true)
    }
}