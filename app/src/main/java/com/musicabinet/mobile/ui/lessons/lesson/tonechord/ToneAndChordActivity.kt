package com.musicabinet.mobile.ui.lessons.lesson.tonechord

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.musicabinet.mobile.Injection
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.setVisible
import com.musicabinet.mobile.model.lesson.machine.ToneOrChord
import com.musicabinet.mobile.ui.lessons.lesson.tonechord.adapter.ToneAndChordAdapter
import kotlinx.android.synthetic.main.activity_tone_and_chord.*
import org.jetbrains.anko.toast

/**
 * @author Kirchhoff-
 */
class ToneAndChordActivity : AppCompatActivity(), ToneAndChordContract.View {

    private val presenter = ToneAndChordPresenter(this, Injection.provideRepository())

    companion object {

        fun requestToneAndChord(activity: Activity, requestCode: Int, tagArg: String,
                                toneResultArg: String, chordResultArg: String) {
            val intent = Intent(activity, ToneAndChordActivity::class.java)
            activity.startActivityForResult(intent, requestCode)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tone_and_chord)
        presenter.subscribe()
    }

    override fun showLoading(show: Boolean) {
        progressBar.setVisible(show)

        rvChord.setVisible(!show)
        rvTone.setVisible(!show)
        tvCancel.setVisible(!show)
        tvOk.setVisible(!show)
    }

    override fun showError() {
        toast(R.string.internal_error)
        finish()
    }

    override fun showTone(list: List<ToneOrChord>) {
        val adapter = ToneAndChordAdapter(list)
        rvTone.layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false)
        rvTone.adapter = adapter
    }

    override fun showChord(list: List<ToneOrChord>) {
        val adapter = ToneAndChordAdapter(list)
        rvChord.layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false)
        rvChord.adapter = adapter
    }

}