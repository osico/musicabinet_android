package com.musicabinet.mobile.ui.lessons.lesson.view.guide.element

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.musicabinet.mobile.R
import kotlinx.android.synthetic.main.view_guide_element.view.*

/**
 * @author Kirchhoff-
 */
class GuideElementView : RelativeLayout, GuideElementContract.View {

    private val presenter = GuideElementPresenter(this)

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
        LayoutInflater.from(context).inflate(R.layout.view_guide_element, this, true)

        ivAddElement.setOnClickListener { }
        ivNotes.setOnClickListener { }
    }
}