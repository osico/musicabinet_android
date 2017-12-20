package com.musicabinet.mobile.ui.view.metronome

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import com.musicabinet.mobile.R
import kotlinx.android.synthetic.main.view_metronome.view.*

/**
 * @author Kirchhoff-
 */
class MetronomeView : ConstraintLayout, MetronomeContract.View {

    private val presenter = MetronomePresenter(this)

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
        LayoutInflater.from(context).inflate(R.layout.view_metronome, this, true)

        ivPlus.setOnClickListener {}
        ivMinus.setOnClickListener {}
    }
}