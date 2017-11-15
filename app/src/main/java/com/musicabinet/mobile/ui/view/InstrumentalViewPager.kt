package com.musicabinet.mobile.ui.view

import android.content.Context
import android.os.Handler
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import com.musicabinet.mobile.ui.instrument.adapter.InstrumentView

/**
 * @author Kirchhoff-
 */
class InstrumentalViewPager : ViewPager {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }


    private fun init() {
        addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                val instrumentItem: InstrumentView? = findViewWithTag<InstrumentView>(currentItem)
                val instrumentNextItem: InstrumentView? = findViewWithTag<InstrumentView>(currentItem + 1)
                val instrumentPreviousItem: InstrumentView? = findViewWithTag<InstrumentView>(currentItem - 1)
                instrumentItem?.showElement()
                instrumentNextItem?.hideElement()
                instrumentPreviousItem?.hideElement()
            }

        })
    }

    public fun showItem() {
        val handler = Handler()
        handler.postDelayed({
            val instrumentItem: InstrumentView? = findViewWithTag<InstrumentView>(currentItem)
            instrumentItem?.showElement()
        }, 100)
    }
}