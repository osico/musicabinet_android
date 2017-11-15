package com.musicabinet.mobile.ui.instrument

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
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

    private var instrumentAdapter: InstrumentAdapter? = null
    private lateinit var presenter: InstrumentContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instrument)

        presenter = InstrumentPresenter(Injection.provideRepository(), this)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

        presenter.loadInstrumentList()
    }


    override fun showLoading(visible: Boolean) {
        progressBar.setVisible(visible)
    }

    override fun showError(visible: Boolean) {
        tvError.setVisible(visible)
    }

    override fun showInstrumentList(visible: Boolean) {
        recyclerView.setVisible(visible)
    }

    override fun setInstrumentList(instrumentList: List<InstrumentDataElement>) {
        if (instrumentAdapter == null) {
            instrumentAdapter = InstrumentAdapter(instrumentList)
            recyclerView.layoutManager = LinearLayoutManager(this,
                    LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = instrumentAdapter
        } else {
            instrumentAdapter?.addItems(instrumentList)
        }
    }

}