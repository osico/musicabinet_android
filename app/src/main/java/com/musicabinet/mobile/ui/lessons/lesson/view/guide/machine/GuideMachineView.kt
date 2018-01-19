package com.musicabinet.mobile.ui.lessons.lesson.view.guide.machine

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.musicabinet.mobile.R
import com.musicabinet.mobile.model.lesson.remote.Accompaniment
import kotlinx.android.synthetic.main.view_guide_machine.view.*

/**
 * @author Kirchhoff-
 */
class GuideMachineView : LinearLayout, GuideMachineContract.View {

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

    fun setAccompaniments(accompaniments: Set<Accompaniment>) {
        soundView.setAccompaniments(accompaniments)
    }
}