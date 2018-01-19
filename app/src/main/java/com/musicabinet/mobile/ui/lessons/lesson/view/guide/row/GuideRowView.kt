package com.musicabinet.mobile.ui.lessons.lesson.view.guide.row

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import com.musicabinet.mobile.R

/**
 * @author Kirchhoff-
 */
class GuideRowView : ConstraintLayout, GuideRowContract.View {

    private val presenter = GuideRowPresenter(this)

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    fun init() {
        LayoutInflater.from(context).inflate(R.layout.view_guide_row, this, true)
    }
}