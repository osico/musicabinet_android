package com.musicabinet.mobile.ui.lessons.list.adapter

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.musicabinet.mobile.R
import com.musicabinet.mobile.model.instrument.matrix.Modules
import kotlinx.android.synthetic.main.view_lesson_title.view.*

/**
 * @author Kirchhoff-
 */
class LessonTitleView : FrameLayout {

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
        LayoutInflater.from(context).inflate(R.layout.view_lesson_title, this, true)
    }


    fun bind(module: Modules) {
        tvTitle.text = module.name
    }
}