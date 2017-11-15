package com.musicabinet.mobile.ui.instrument.adapter

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.musicabinet.mobile.model.instrument.InstrumentDataElement

/**
 * @author Kirchhoff-
 */
class InstrumentAdapter(private val instrumentList: List<InstrumentDataElement>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val instrumentView = InstrumentView(container.context)
        instrumentView.bind(instrumentList[position])
        container.addView(instrumentView)
        return instrumentView
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View)
    }

    override fun getCount() = instrumentList.size

    override fun isViewFromObject(view: View, `object`: Any) = view === `object`

}