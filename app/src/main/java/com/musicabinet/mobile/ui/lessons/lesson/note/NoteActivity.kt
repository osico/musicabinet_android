package com.musicabinet.mobile.ui.lessons.lesson.note

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.AdapterView
import com.musicabinet.mobile.Constants
import com.musicabinet.mobile.Injection
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.setVisible
import com.musicabinet.mobile.model.lesson.machine.ToneOrChordResult
import com.musicabinet.mobile.model.lesson.machine.note.NoteItem
import com.musicabinet.mobile.model.lesson.machine.note.image.NoteElement
import com.musicabinet.mobile.ui.ActionBarActivity
import com.musicabinet.mobile.ui.lessons.lesson.note.adapter.image.NoteImageAdapter
import com.musicabinet.mobile.ui.lessons.lesson.note.adapter.spinner.NoteSpinnerAdapter
import kotlinx.android.synthetic.main.activity_note.*
import org.jetbrains.anko.toast

/**
 * @author Kirchhoff-
 */
class NoteActivity : ActionBarActivity(), NoteContract.View, NoteImageAdapter.NoteItemSelected, AdapterView.OnItemSelectedListener {

    companion object {
        fun requestNote(activity: Activity, toneOrChordArg: ToneOrChordResult,
                        noteElement: NoteElement?, tagArg: String) {
            val intent = Intent(activity, NoteActivity::class.java)
            intent.putExtra(Constants.NOTE_ELEMENT_ARG, toneOrChordArg)
            intent.putExtra(Constants.NOTE_SELECTED_ELEMENT_ARG, noteElement)
            intent.putExtra(Constants.NOTE_TAG_ARG, tagArg)
            activity.startActivityForResult(intent, Constants.NOTE_REQUEST_CODE)
        }
    }

    val presenter = NotePresenter(Injection.provideRepository(), Injection.provideStorage(),
            this)

    private lateinit var toneOrChordArg: ToneOrChordResult
    private lateinit var adapter: NoteImageAdapter
    //For fixing android bug
    private var selectedItemCount = 0
    private lateinit var instrumentAdapter: NoteSpinnerAdapter
    private lateinit var moduleAdapter: NoteSpinnerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        toneOrChordArg = intent.getParcelableExtra(Constants.NOTE_ELEMENT_ARG)

        title = toneOrChordArg.tone.name + " " + toneOrChordArg.chord.name

        presenter.subscribe(toneOrChordArg)

        bSave.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra(Constants.NOTE_TAG_ARG,
                    intent.getStringExtra(Constants.NOTE_TAG_ARG))
            resultIntent.putExtra(Constants.NOTE_RESULT_ARG,
                    adapter.getSelectedElement())
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        bSave.isEnabled = false
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribe()
    }

    override fun showLoading(show: Boolean) {
        progressBar.setVisible(show)

        spinnerInstrument.setVisible(!show)
        spinnerModule.setVisible(!show)
        recyclerView.setVisible(!show)
    }

    override fun showNoteLoading(show: Boolean) {
        progressBar.setVisible(show)

        recyclerView.setVisible(!show)
    }

    override fun showError() {
        toast(R.string.internal_error)
        finish()
    }

    override fun showInstrument(list: List<NoteItem>) {
        instrumentAdapter = NoteSpinnerAdapter(this, R.layout.item_note_spinner, list)
        spinnerInstrument.adapter = instrumentAdapter
        spinnerInstrument.onItemSelectedListener = this
    }

    override fun showModule(list: List<NoteItem>) {
        moduleAdapter = NoteSpinnerAdapter(this, R.layout.item_note_spinner, list)
        spinnerModule.adapter = moduleAdapter
        spinnerModule.onItemSelectedListener = this
    }

    override fun showNoteImage(list: List<NoteElement>) {
        val noteItem: NoteElement? = intent.getParcelableExtra(Constants.NOTE_SELECTED_ELEMENT_ARG)
        adapter = NoteImageAdapter(list, noteItem, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this,
                4)
        recyclerView.setHasFixedSize(true)

        val selectedPosition = adapter.getSelectedElementPosition()
        if (selectedPosition != 0)
            recyclerView.scrollToPosition(selectedPosition)
    }

    override fun onItemSelected(isSelected: Boolean) {
        bSave.isEnabled = isSelected
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (selectedItemCount > 1) {
            presenter.getNoteDiagram(toneOrChordArg,
                    moduleAdapter.getItem(spinnerModule.selectedItemPosition).id,
                    instrumentAdapter.getItem(spinnerInstrument.selectedItemPosition).id)
        } else {
            selectedItemCount++
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        //Not needed
    }
}