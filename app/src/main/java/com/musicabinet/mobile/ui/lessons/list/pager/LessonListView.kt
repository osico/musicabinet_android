package com.musicabinet.mobile.ui.lessons.list.pager

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.setVisible
import com.musicabinet.mobile.model.instrument.matrix.LessonItem
import com.musicabinet.mobile.model.instrument.matrix.local.InstrumentGroup
import com.musicabinet.mobile.ui.lessons.list.adapter.LessonAdapter
import com.musicabinet.mobile.ui.lessons.list.adapter.LessonItemView
import kotlinx.android.synthetic.main.view_lesson_list.view.*

/**
 * @author Kirchhoff-
 */
class LessonListView : FrameLayout, LessonItemView.OnLessonItemClickListener {

    private lateinit var lessonAdapter: LessonAdapter
    private var buyButtonListener: LessonBuyButtonListener? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        LayoutInflater.from(context).inflate(R.layout.view_lesson_list, this, true)
        lessonRecyclerView.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false)
        lessonRecyclerView.isNestedScrollingEnabled = false

        bBuy.setOnClickListener {
            buyButtonListener?.onBuyButtonClick()
        }
    }

    fun setLessonList(list: List<InstrumentGroup>) {
        lessonAdapter = LessonAdapter(list, this)
        lessonRecyclerView.adapter = lessonAdapter
    }

    override fun onItemClick(item: LessonItem) {
        buyButtonListener?.onItemClick(item)
    }


    fun setProductPrice(price: Float, productActive: Boolean, productAvailable: Boolean?) {
        if (productActive && productAvailable != null && !productAvailable && price != 0f) {
            bBuy.setVisible(true)
            bBuy.text = resources.getString(R.string.price_buy, price)
        } else {
            bBuy.setVisible(false)
        }
    }

    fun setProductName(name: String) {
        tvName.text = name
    }

    fun setBuyButtonListener(listener: LessonBuyButtonListener) {
        buyButtonListener = listener
    }

    interface LessonBuyButtonListener {

        fun onBuyButtonClick()

        fun onItemClick(item: LessonItem)
    }
}