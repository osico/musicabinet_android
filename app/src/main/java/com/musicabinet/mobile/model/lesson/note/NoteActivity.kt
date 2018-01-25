package com.musicabinet.mobile.model.lesson.note

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.musicabinet.mobile.Injection
import com.musicabinet.mobile.R

/**
 * @author Kirchhoff-
 */
class NoteActivity : AppCompatActivity(), NoteContract.View {

    val presenter = NotePresenter(Injection.provideRepository(), Injection.provideStorage(),
            this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
    }
}