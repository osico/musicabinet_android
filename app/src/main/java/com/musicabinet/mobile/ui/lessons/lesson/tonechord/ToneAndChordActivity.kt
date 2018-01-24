package com.musicabinet.mobile.ui.lessons.lesson.tonechord

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.musicabinet.mobile.R
import kotlinx.android.synthetic.main.activity_tone_and_chord.*

/**
 * @author Kirchhoff-
 */
class ToneAndChordActivity : AppCompatActivity(), ToneAndChordContract.View {

    companion object {

        fun requestToneAndChord(activity: Activity, requestCode: Int, toneResultArg: String,
                                chordResultArg: String) {
            val intent = Intent(activity, ToneAndChordActivity::class.java)
            activity.startActivityForResult(intent, requestCode)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tone_and_chord)

        numberPicker.setMinValue(0)
        numberPicker.setMaxValue(10)
        numberPicker.setDisplayedValues(arrayOf("C#[Db]", "D", "D#[Db]",
                "C#[Db]", "D", "D#[Db]",
                "C#[Db]", "D", "D#[Db]",
                "C#[Db]", "D", "D#[Db]"))

        numberPicker2.setMinValue(0)
        numberPicker2.setMaxValue(10)
        numberPicker2.setDisplayedValues(arrayOf("Maj7", "M7", "Dom7 (Alt)",
                "Maj7", "M7", "Dom7 (Alt)",
                "Maj7", "M7", "Dom7 (Alt)",
                "Maj7", "M7", "Dom7 (Alt)"))
    }

}