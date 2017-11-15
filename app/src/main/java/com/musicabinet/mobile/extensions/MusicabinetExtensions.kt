package com.musicabinet.mobile.extensions

import android.widget.ImageView
import com.musicabinet.mobile.R

/**
 * @author Kirchhoff-
 */

const val INSTRUMENT_DRUMS = "drums"
const val INSTRUMENT_BASS = "bass"
const val INSTRUMENT_KEYBOARD = "keyboard"
const val INSTRUMENT_GUITAR = "guitar"
const val INSTRUMENT_SAXOPHONE = "saxophone"
const val INSTRUMENT_VOCAL = "vocal"
const val INSTRUMENT_COMPOSITION = "composition"

fun ImageView.bindInstrumentImage(instrument: String) {
    when (instrument) {
        INSTRUMENT_DRUMS -> setImageResource(R.drawable.ic_instrument_drums)
        INSTRUMENT_BASS -> setImageResource(R.drawable.ic_instrument_bass)
        INSTRUMENT_KEYBOARD -> setImageResource(R.drawable.ic_instrument_piano)
        INSTRUMENT_GUITAR -> setImageResource(R.drawable.ic_instrument_guitar)
        INSTRUMENT_SAXOPHONE -> setImageResource(R.drawable.ic_instrument_sux)
        INSTRUMENT_VOCAL -> setImageResource(R.drawable.ic_instrument_vocal)
        INSTRUMENT_COMPOSITION -> setImageResource(R.drawable.ic_instrument_note)
    }
}