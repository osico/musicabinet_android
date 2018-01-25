package com.musicabinet.mobile.ui.instrument

import com.musicabinet.mobile.model.instrument.InstrumentDataElement

/**
 * @author Kirchhoff-
 */
interface InstrumentContract {

    interface View {

        fun showLoading(visible: Boolean)

        fun showError(visible: Boolean)

        fun showInstrumentList(visible: Boolean)

        fun showPageIndicator(visible: Boolean)

        fun setInstrumentList(instrumentList: List<InstrumentDataElement>)

        fun moveToCourseActivity(name: String, id: String)

    }


    interface Presenter {

        fun loadInstrumentList()

        fun onInstrumentSelected(instrument: InstrumentDataElement)

        fun unsubscribe()

    }
}