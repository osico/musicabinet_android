package com.musicabinet.mobile.ui.lessons.lesson.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.setVisible
import com.musicabinet.mobile.model.lesson.remote.Accompaniment
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


    fun setAccompaniments(accompaniments: Set<Accompaniment>) {

        setVisible(!accompaniments.isEmpty())

        val list = ArrayList<Accompaniment>(accompaniments)
        val adapter = SoundViewAdapter(context, R.layout.item_sound_spinner, list)
        sRoad.adapter = adapter

        for (accompaniment in list) {

            if (accompaniment.drums != null && accompaniment.drums.dataAvailable)
                cDrums.isChecked = true

            if (accompaniment.bass != null && accompaniment.bass.dataAvailable)
                cBass.isChecked = true

            if (accompaniment.keys != null && accompaniment.keys.dataAvailable)
                cKeys.isChecked = true
        }

        cDrums.isEnabled = cDrums.isChecked
        cBass.isEnabled = cBass.isChecked
        cKeys.isEnabled = cKeys.isChecked
    }
}