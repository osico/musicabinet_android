package com.musicabinet.mobile.ui.instrument

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.musicabinet.mobile.Injection
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.setVisible
import com.musicabinet.mobile.model.instrument.InstrumentDataElement
import com.musicabinet.mobile.ui.instrument.adapter.InstrumentAdapter
import kotlinx.android.synthetic.main.activity_instrument.*

/**
 * @author Kirchhoff-
 */
class InstrumentActivity : AppCompatActivity(), InstrumentContract.View {

    private lateinit var instrumentAdapter: InstrumentAdapter
    private lateinit var presenter: InstrumentContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instrument)

        val pagerPadding = resources.getDimensionPixelSize(R.dimen.instrument_item_padding)
        //viewPager.clipToPadding = false
        //viewPager.setPadding(pagerPadding, 0, pagerPadding, 0)

        presenter = InstrumentPresenter(Injection.provideRepository(), this)

        presenter.loadInstrumentList()
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

    override fun setInstrumentList(instrumentList: List<InstrumentDataElement>) {
        instrumentAdapter = InstrumentAdapter(instrumentList)
        viewPager.adapter = instrumentAdapter
        viewPager.currentItem = instrumentAdapter.count / 2

        viewPager.showItem()
    }

}