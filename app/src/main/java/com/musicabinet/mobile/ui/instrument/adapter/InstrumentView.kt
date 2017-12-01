package com.musicabinet.mobile.ui.instrument.adapter

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.bindInstrumentImage
import com.musicabinet.mobile.extensions.setVisible
import com.musicabinet.mobile.model.instrument.InstrumentDataElement
import kotlinx.android.synthetic.main.view_instrument.view.*

/**
 * @author Kirchhoff-
 */
class InstrumentView : LinearLayout {

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
        LayoutInflater.from(context).inflate(R.layout.view_instrument, this, true)
        isClickable = true
    }

    fun bind(instrument: InstrumentDataElement) {
        with(instrument) {
            tvName.text = nameLocalized
            tvNotAvailable.setVisible(!active)
            ivInstrument.bindInstrumentImage(logo)
        }
    }
}