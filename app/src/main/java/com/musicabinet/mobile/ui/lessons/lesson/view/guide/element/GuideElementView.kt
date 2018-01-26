package com.musicabinet.mobile.ui.lessons.lesson.view.guide.element

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.setVisible
import com.musicabinet.mobile.model.lesson.machine.ToneOrChordResult
import com.musicabinet.mobile.ui.lessons.lesson.note.NoteActivity
import com.musicabinet.mobile.ui.lessons.lesson.tonechord.ToneAndChordActivity
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

        fabAddElement.setOnClickListener { presenter.requestToneAndChord() }
        ivNotes.setOnClickListener { presenter.requestNote() }

        presenter.subscribe()
    }

    override fun enableFabClick(enable: Boolean) {
        fabAddElement.isEnabled = enable
    }

    override fun enableNoteClick(enable: Boolean) {
        ivNotes.isEnabled = enable
    }

    override fun requestToneAndChord(requestCode: Int, tagArg: String) {
        ToneAndChordActivity.requestToneAndChord(context as Activity, requestCode, tag.toString())
    }

    fun setToneAndChord(toneAndChordResult: ToneOrChordResult) {
        presenter.showToneAndChord(toneAndChordResult)
    }

    override fun showAddButton(show: Boolean) {
        if (show)
            fabAddElement.visibility = View.VISIBLE
        else
            fabAddElement.visibility = View.INVISIBLE
    }

    override fun showToneAndChord(show: Boolean) {
        toneAndChordLayout.setVisible(show)
    }

    override fun setTone(tone: String) {
        tvTone.text = tone
    }

    override fun setChord(chord: String) {
        tvChord.text = chord
    }

    override fun requestNote(toneAndChordResult: ToneOrChordResult) {
        NoteActivity.requestNote(context as Activity, toneAndChordResult, tag.toString())
    }

}