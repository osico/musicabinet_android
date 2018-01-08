package com.musicabinet.mobile.extensions

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import android.support.v7.app.AlertDialog
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.ImageView
import android.widget.TextView
import com.musicabinet.mobile.Injection
import com.musicabinet.mobile.R
import com.squareup.picasso.Picasso

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

const val LESSON_IMAGE_URL = "https://api.musicabinet.com/platform/api/file-storage/"
const val DOWNLOAD = "/download"

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


fun TextView.setCompletedPercent(percent: Float?) {
    if (Injection.provideStorage().isUserExist() && percent != null) {
        setVisible(true)
        text = (percent * 100).toInt().toString() + "%"

        alpha = if (percent == 0f)
            0.5f
        else
            1f
    } else {
        setVisible(false)
    }
}

fun ImageView.loadLessonImage(url: String?) {
    if (!url.isNullOrEmpty())
        Picasso.with(context).load(LESSON_IMAGE_URL + url + DOWNLOAD).into(this)
}


fun Activity.createPaymentDialog() {
    val title = getString(R.string.buy_dialog_title)
    val foregroundColorSpan = ForegroundColorSpan(Color.BLACK)
    val ssBuilder = SpannableStringBuilder(title)
    ssBuilder.setSpan(
            foregroundColorSpan,
            0,
            title.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    );
    val alertDialog = AlertDialog.Builder(this)
            .setTitle(ssBuilder)
            .setMessage(R.string.buy_dialog_text)
            .setPositiveButton(R.string.go_to_site) { _, _ ->
                val browserIntent = Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://app.musicabinet.com"))
                startActivity(browserIntent)
            }
            .setNegativeButton(R.string.cancel, null)
            .create()

    alertDialog.show()
    alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).textColor(R.color.colorPrimary)
    alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).textColor(R.color.colorPrimary)
}

fun MediaPlayer.play() {

    setOnCompletionListener { start() }
    start()
}
