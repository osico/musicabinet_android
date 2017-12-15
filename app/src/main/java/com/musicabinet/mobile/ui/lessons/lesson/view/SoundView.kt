package com.musicabinet.mobile.ui.lessons.lesson.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import com.musicabinet.mobile.R
import kotlinx.android.synthetic.main.view_sound.view.*

/**
 * @author Kirchhoff-
 */
class SoundView : ConstraintLayout {

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
        LayoutInflater.from(context).inflate(R.layout.view_sound, this, true)

        cDrums.setOnClickListener { cDrums.isChecked = !cDrums.isChecked }
        cBass.setOnClickListener { cBass.isChecked = !cBass.isChecked }
        cKeys.setOnClickListener { cKeys.isChecked = !cKeys.isChecked }
    }
}