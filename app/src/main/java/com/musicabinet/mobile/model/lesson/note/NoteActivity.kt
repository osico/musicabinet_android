package com.musicabinet.mobile.model.lesson.note

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.musicabinet.mobile.Constants
import com.musicabinet.mobile.Injection
import com.musicabinet.mobile.R
import com.musicabinet.mobile.model.lesson.machine.ToneOrChordResult
import com.musicabinet.mobile.ui.ActionBarActivity

/**
 * @author Kirchhoff-
 */
class NoteActivity : ActionBarActivity(), NoteContract.View {

    companion object {
        fun requestNote(activity: Activity, toneOrChordArg: ToneOrChordResult, tagArg: String) {
            val intent = Intent(activity, NoteActivity::class.java)
            intent.putExtra(Constants.NOTE_ELEMENT_ARG, toneOrChordArg)
            intent.putExtra(Constants.NOTE_TAG_ARG, tagArg)
            activity.startActivityForResult(intent, Constants.NOTE_REQUEST_CODE)
        }
    }

    val presenter = NotePresenter(Injection.provideRepository(), Injection.provideStorage(),
            this)

    private lateinit var toneOrChordArg: ToneOrChordResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        toneOrChordArg = intent.getParcelableExtra(Constants.NOTE_ELEMENT_ARG)

        title = toneOrChordArg.tone.name + " " + toneOrChordArg.chord.name
    }
}