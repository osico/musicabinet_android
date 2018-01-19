package com.musicabinet.mobile.ui.lessons.lesson.view.guide.machine

import android.content.Context
import android.support.v4.widget.NestedScrollView
import android.util.AttributeSet
import android.view.LayoutInflater
import com.musicabinet.mobile.R

/**
 * @author Kirchhoff-
 */
class GuideMachineView : NestedScrollView, GuideMachineContract.View {

    private val presenter = GuideMachinePresenter(this)

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
        LayoutInflater.from(context).inflate(R.layout.view_guide_machine, this, true)
    }
}