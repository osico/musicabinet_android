package com.musicabinet.mobile.ui.lessons.lesson.view.guide.machine

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.Toast
import com.musicabinet.mobile.Constants
import com.musicabinet.mobile.Injection
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.setVisible
import com.musicabinet.mobile.model.lesson.remote.Accompaniment
import com.musicabinet.mobile.model.lesson.remote.Stave
import com.musicabinet.mobile.ui.lessons.lesson.view.guide.element.GuideElementView
import com.musicabinet.mobile.ui.lessons.lesson.view.guide.row.GuideRowView
import kotlinx.android.synthetic.main.view_guide_machine.view.*

/**
 * @author Kirchhoff-
 */
class GuideMachineView : LinearLayout, GuideMachineContract.View {

    private val presenter = GuideMachinePresenter(this, Injection.provideRepository(), context.filesDir)

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

        //presenter.subscribe()
    }

    override fun addRow(row: Int) {

        val rowView = GuideRowView(context)
        rowView.setRowTag(row.toString())

        guideMachineLayout.addView(rowView)
    }

    fun setAccompaniments(accompaniments: Set<Accompaniment>) {
        soundView.setAccompaniments(accompaniments)
    }

    fun setStave(stave: Stave?) {
        presenter.subscribe(stave)
    }

    fun onPause() {
        soundView.onPause()
    }

    override fun showLoading(show: Boolean) {
        guideProgressBar.setVisible(show)
        guideMachineLayout.setVisible(!show)
    }

    override fun showError() {
        Toast.makeText(context, R.string.guide_machine_download_file_error, Toast.LENGTH_SHORT).show()
    }

    override fun showImprovisationNote() {
        Toast.makeText(context, "ShowImprovisationNote", Toast.LENGTH_SHORT).show()
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == Constants.GUIDE_MACHINE_REQUEST_CODE && resultCode == Activity.RESULT_OK &&
                data != null) {

            val resultTag: String = data.getStringExtra(Constants.GUIDE_MACHINE_TAG_RESULT_ARG)
            val row = resultTag.substring(resultTag.lastIndexOf("}") + 1, resultTag.length - 1)
            presenter.onElementSelected(row)
            val guideElementView: GuideElementView = guideMachineLayout
                    .findViewWithTag(resultTag)

            guideElementView.setToneAndChord(data.getParcelableExtra(Constants.GUIDE_MACHINE_ELEMENT_RESULT_ARG))
        } else if (requestCode == Constants.NOTE_REQUEST_CODE && resultCode == Activity.RESULT_OK &&
                data != null) {
            val resultTag: String = data.getStringExtra(Constants.NOTE_TAG_ARG)
            val guideElementView: GuideElementView = guideMachineLayout
                    .findViewWithTag(resultTag)

            guideElementView.setNoteImage(data.getParcelableExtra(Constants.NOTE_RESULT_ARG))
        }
    }
}