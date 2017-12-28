package com.musicabinet.mobile.ui.lessons.lesson.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.configVisibility
import com.musicabinet.mobile.extensions.setVisible
import com.musicabinet.mobile.model.lesson.remote.Accompaniment
import kotlinx.android.synthetic.main.view_sound.view.*

/**
 * @author Kirchhoff-
 */
class SoundView : ConstraintLayout, AdapterView.OnItemSelectedListener {

    private lateinit var accompanimentsList: ArrayList<Accompaniment>

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

        accompanimentsList = ArrayList(accompaniments)
        setupAdapter(accompanimentsList)
        showAccompaniment(accompanimentsList[0])

        var shouldShowElement = false
        for (accompaniment in accompanimentsList) {
            if (accompaniment.keys != null && accompaniment.keys.dataAvailable) {
                shouldShowElement = true
                break
            }

            if (accompaniment.drums != null && accompaniment.drums.dataAvailable) {
                shouldShowElement = true
                break
            }

            if (accompaniment.bass != null && accompaniment.bass.dataAvailable) {
                shouldShowElement = true
                break
            }
        }

        setVisible(shouldShowElement)
    }

    private fun setupAdapter(accompaniments: List<Accompaniment>) {
        val adapter = SoundViewAdapter(context, R.layout.item_sound_spinner, accompaniments)
        sRoad.adapter = adapter

        sRoad.onItemSelectedListener = this
    }

    private fun showAccompaniment(accompaniment: Accompaniment) {
        if (accompaniment.drums != null && accompaniment.drums.dataAvailable)
            cDrums.isChecked = true

        if (accompaniment.bass != null && accompaniment.bass.dataAvailable)
            cBass.isChecked = true

        if (accompaniment.keys != null && accompaniment.keys.dataAvailable)
            cKeys.isChecked = true


        cDrums.configVisibility()
        cBass.configVisibility()
        cKeys.configVisibility()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        showAccompaniment(accompanimentsList[position])
    }
}