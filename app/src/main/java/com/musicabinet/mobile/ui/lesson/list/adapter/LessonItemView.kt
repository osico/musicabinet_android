package com.musicabinet.mobile.ui.lesson.list.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import com.musicabinet.mobile.R
import com.musicabinet.mobile.model.instrument.matrix.LessonItem
import kotlinx.android.synthetic.main.view_lesson.view.*

/**
 * @author Kirchhoff-
 */
class LessonItemView : ConstraintLayout {

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

    fun bind(element: LessonItem) {
        with(element) {
            tvName.text = nameLocalized
            tvPercent.text = progress.toString()
        }
    }
}