package com.musicabinet.mobile.ui.lessons.lesson.view.guide.row

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.musicabinet.mobile.R
import kotlinx.android.synthetic.main.view_guide_row.view.*

/**
 * @author Kirchhoff-
 */
class GuideRowView : LinearLayout, GuideRowContract.View {

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

    fun setRowTag(position: String) {
        tag = position

        firstElement.tag = position + "1"
        secondElement.tag = position + "2"
        thirdElement.tag = position + "3"
        fourthElement.tag = position + "4"
    }
}