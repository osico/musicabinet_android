package com.musicabinet.mobile.ui.instrument

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.musicabinet.mobile.Injection
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.setVisible
import com.musicabinet.mobile.model.instrument.InstrumentDataElement
import com.musicabinet.mobile.ui.courses.CoursesActivity
import com.musicabinet.mobile.ui.instrument.adapter.InstrumentAdapter
import com.musicabinet.mobile.ui.slidemenu.SlideMenuActivity
import kotlinx.android.synthetic.main.activity_instrument.*

/**
 * @author Kirchhoff-
 */
class InstrumentActivity : SlideMenuActivity(), InstrumentContract.View, InstrumentAdapter.OnInstrumentClickListener {

    private val presenter = InstrumentPresenter(Injection.provideRepository(), Injection.provideStorage(), this)

    private lateinit var instrumentAdapter: InstrumentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.loadInstrumentList()
    }

    @SuppressLint("InflateParams")
    override fun inflateLayout(): View {
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        return inflater.inflate(R.layout.activity_instrument, null, false)
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribe()
    }

    override fun showLoading(visible: Boolean) {
        progressBar.setVisible(visible)
    }

    override fun showError(visible: Boolean) {
        tvError.setVisible(visible)
    }

    override fun showInstrumentList(visible: Boolean) {
        viewPager.setVisible(visible)
    }

    override fun showPageIndicator(visible: Boolean) {
        pageIndicator.setVisible(visible)
    }

    override fun setInstrumentList(instrumentList: List<InstrumentDataElement>) {
        instrumentAdapter = InstrumentAdapter(instrumentList)
        viewPager.adapter = instrumentAdapter
        viewPager.currentItem = instrumentAdapter.count / 2
        pageIndicator.setViewPager(viewPager)
        pageIndicator.selection = instrumentAdapter.count / 2

        instrumentAdapter.setOnInstrumentClickListener(this)
    }

    override fun moveToCourseActivity(name: String, id: String) {
        val intent = Intent(this, CoursesActivity::class.java)
        intent.putExtra(CoursesActivity.INSTRUMENT_NAME_ARG, name)
        intent.putExtra(CoursesActivity.INSTRUMENT_ID_ARG, id)
        startActivity(intent)
    }

    override fun onInstrumentSelected(instrument: InstrumentDataElement) {
        presenter.onInstrumentSelected(instrument)
    }
}