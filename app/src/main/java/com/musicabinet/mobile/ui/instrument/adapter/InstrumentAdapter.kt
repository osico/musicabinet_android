package com.musicabinet.mobile.ui.instrument.adapter

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.musicabinet.mobile.model.instrument.InstrumentDataElement

/**
 * @author Kirchhoff-
 */
class InstrumentAdapter(private val instrumentList: List<InstrumentDataElement>) : PagerAdapter() {

    private var instrumentClickListener: OnInstrumentClickListener? = null

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val instrumentView = InstrumentView(container.context)
        instrumentView.bind(instrumentList[position])
        instrumentView.tag = position
        instrumentView.setOnClickListener {
            if (instrumentList[position].active)
                instrumentClickListener?.onInstrumentSelected(instrumentList[position])
        }
        container.addView(instrumentView)
        return instrumentView
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View)
    }

    override fun getCount() = instrumentList.size

    override fun isViewFromObject(view: View, `object`: Any) = view === `object`

    override fun getItemPosition(`object`: Any) = PagerAdapter.POSITION_NONE

    fun setOnInstrumentClickListener(listener: OnInstrumentClickListener) {
        instrumentClickListener = listener
    }

    interface OnInstrumentClickListener {

        fun onInstrumentSelected(instrument: InstrumentDataElement)
    }

}