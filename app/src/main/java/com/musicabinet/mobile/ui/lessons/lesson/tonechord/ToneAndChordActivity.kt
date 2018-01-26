package com.musicabinet.mobile.ui.lessons.lesson.tonechord

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.musicabinet.mobile.Constants
import com.musicabinet.mobile.Injection
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.setVisible
import com.musicabinet.mobile.model.lesson.machine.ToneOrChord
import com.musicabinet.mobile.model.lesson.machine.ToneOrChordResult
import com.musicabinet.mobile.ui.lessons.lesson.tonechord.adapter.ToneAndChordAdapter
import kotlinx.android.synthetic.main.activity_tone_and_chord.*
import org.jetbrains.anko.toast

/**
 * @author Kirchhoff-
 */
class ToneAndChordActivity : AppCompatActivity(), ToneAndChordContract.View {

    private val presenter = ToneAndChordPresenter(this, Injection.provideRepository())

    //Just for test , remove in feature
    private lateinit var firstToneOrChord: ToneOrChord
    private lateinit var secondToneOrChord: ToneOrChord

    companion object {

        fun requestToneAndChord(activity: Activity, requestCode: Int, tagArg: String) {
            val intent = Intent(activity, ToneAndChordActivity::class.java)
            intent.putExtra(Constants.GUIDE_MACHINE_TAG_RESULT_ARG, tagArg)
            activity.startActivityForResult(intent, requestCode)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tone_and_chord)
        presenter.subscribe()


        tvCancel.setOnClickListener {
            finish()
        }

        tvOk.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra(Constants.GUIDE_MACHINE_TAG_RESULT_ARG,
                    intent.getStringExtra(Constants.GUIDE_MACHINE_TAG_RESULT_ARG))
            resultIntent.putExtra(Constants.GUIDE_MACHINE_ELEMENT_RESULT_ARG,
                    ToneOrChordResult(firstToneOrChord, secondToneOrChord))
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
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
        firstToneOrChord = list[0]
    }

    override fun showChord(list: List<ToneOrChord>) {
        val adapter = ToneAndChordAdapter(list)
        rvChord.layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false)
        rvChord.adapter = adapter
        secondToneOrChord = list[0]
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribe()
    }

}