package com.musicabinet.mobile.ui.lessons.lesson.tonechord

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.musicabinet.mobile.Constants
import com.musicabinet.mobile.Injection
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.setVisible
import com.musicabinet.mobile.model.lesson.machine.ToneOrChord
import com.musicabinet.mobile.model.lesson.machine.ToneOrChordResult
import kotlinx.android.synthetic.main.activity_tone_and_chord.*
import org.jetbrains.anko.toast

/**
 * @author Kirchhoff-
 */
class ToneAndChordActivity : AppCompatActivity(), ToneAndChordContract.View {

    private val presenter = ToneAndChordPresenter(this, Injection.provideRepository())
    private var toneOrChordResultArg: ToneOrChordResult? = null
    private var toneCodeArg: String? = null
    private var chordCodeArg: String? = null

    companion object {

        fun requestToneAndChord(activity: Activity, requestCode: Int,
                                toneOrChordResult: ToneOrChordResult?,
                                toneCode: String?, chordCode: String?, tagArg: String) {
            val intent = Intent(activity, ToneAndChordActivity::class.java)
            intent.putExtra(Constants.GUIDE_MACHINE_TAG_RESULT_ARG, tagArg)
            intent.putExtra(Constants.GUIDE_MACHINE_TONE_CODE_ARG, toneCode)
            intent.putExtra(Constants.GUIDE_MACHINE_CHORD_CODE_ARG, chordCode)
            intent.putExtra(Constants.GUIDE_MACHINE_ELEMENT_ARG, toneOrChordResult)
            activity.startActivityForResult(intent, requestCode)
        }
    }

    private lateinit var toneList: List<ToneOrChord>
    private lateinit var chordList: List<ToneOrChord>

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
                    ToneOrChordResult(toneList[npTone.value], chordList[npChord.value]))
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        toneOrChordResultArg = intent.getParcelableExtra(Constants.GUIDE_MACHINE_ELEMENT_ARG)
        toneCodeArg = intent.getStringExtra(Constants.GUIDE_MACHINE_TONE_CODE_ARG)
        chordCodeArg = intent.getStringExtra(Constants.GUIDE_MACHINE_CHORD_CODE_ARG)
    }

    override fun showLoading(show: Boolean) {
        progressBar.setVisible(show)

        npChord.setVisible(!show)
        npTone.setVisible(!show)
        tvCancel.setVisible(!show)
        tvOk.setVisible(!show)
    }

    override fun showError() {
        toast(R.string.internal_error)
        finish()
    }

    override fun showTone(list: List<ToneOrChord>) {
        toneList = list
        val toneStringList = ArrayList<String>()
        for (item in list)
            toneStringList.add(item.name)
        npTone.minValue = 0
        npTone.maxValue = list.size - 1
        npTone.displayedValues = toneStringList.toTypedArray()

        if (toneOrChordResultArg != null)
            npTone.value = getItemPosition(list, toneOrChordResultArg!!.tone.id)
        else if (toneCodeArg != null)
            npTone.value = getItemPosition(list, toneCodeArg!!)
    }

    override fun showChord(list: List<ToneOrChord>) {
        chordList = list
        val chordStringList = ArrayList<String>()
        for (item in list)
            chordStringList.add(item.name)
        npChord.minValue = 0
        npChord.maxValue = list.size - 1
        npChord.displayedValues = chordStringList.toTypedArray()

        if (toneOrChordResultArg != null)
            npChord.value = getItemPosition(list, toneOrChordResultArg!!.chord.id)
        else if (chordCodeArg != null)
            npChord.value = getItemPosition(list, chordCodeArg!!)

    }

    private fun getItemPosition(list: List<ToneOrChord>, codeOrId: String): Int {
        for (i in list.indices)
            if (list[i].id == codeOrId) {
                return i
            }

        return 0
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribe()
    }

}