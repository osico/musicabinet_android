package com.musicabinet.mobile.ui.instrument

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.musicabinet.mobile.Injection
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.setVisible
import com.musicabinet.mobile.model.instrument.InstrumentDataElement
import com.musicabinet.mobile.ui.instrument.adapter.InstrumentAdapter
import com.musicabinet.mobile.ui.slidemenu.SlideMenuActivity
import kotlinx.android.synthetic.main.activity_instrument.*

/**
 * @author Kirchhoff-
 */
class InstrumentActivity : SlideMenuActivity(), InstrumentContract.View {

    private lateinit var instrumentAdapter: InstrumentAdapter
    private lateinit var presenter: InstrumentContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = InstrumentPresenter(Injection.provideRepository(), this)

        presenter.loadInstrumentList()
    }

    override fun inflateLayout(): View {
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        return inflater.inflate(R.layout.activity_instrument, null, false)
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
    }

}