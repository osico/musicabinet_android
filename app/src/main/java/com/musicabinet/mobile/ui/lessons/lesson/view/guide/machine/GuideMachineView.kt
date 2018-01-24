package com.musicabinet.mobile.ui.lessons.lesson.view.guide.machine

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.musicabinet.mobile.Constants
import com.musicabinet.mobile.R
import com.musicabinet.mobile.model.lesson.remote.Accompaniment
import com.musicabinet.mobile.ui.lessons.lesson.view.guide.element.GuideElementView
import com.musicabinet.mobile.ui.lessons.lesson.view.guide.row.GuideRowView
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

        presenter.subscribe()
    }

    override fun addRow(row: Int) {

        val row = GuideRowView(context)
        row.setRowTag(row.toString())

        machineLayout.addView(row)
    }

    fun setAccompaniments(accompaniments: Set<Accompaniment>) {
        soundView.setAccompaniments(accompaniments)
    }

    fun onPause() {
        soundView.onPause()
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == Constants.GUIDE_MACHINE_REQUEST_CODE && resultCode == Activity.RESULT_OK &&
                data != null) {

            val guideElementView: GuideElementView = machineLayout
                    .findViewWithTag(data.getStringExtra(Constants.GUIDE_MACHINE_TAG_RESULT_ARG))

            guideElementView.showToneAndChord(data.getParcelableExtra(Constants.GUIDE_MACHINE_ELEMENT_RESULT_ARG))
        }
    }
}