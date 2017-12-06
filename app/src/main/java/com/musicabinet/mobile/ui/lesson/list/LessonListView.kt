package com.musicabinet.mobile.ui.lesson.list

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.setVisible
import com.musicabinet.mobile.model.instrument.matrix.local.InstrumentGroup
import com.musicabinet.mobile.ui.lesson.list.adapter.LessonAdapter
import kotlinx.android.synthetic.main.view_lesson_list.view.*

/**
 * @author Kirchhoff-
 */
class LessonListView : FrameLayout {

    private lateinit var lessonAdapter: LessonAdapter

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
    }

    fun setLessonList(list: List<InstrumentGroup>) {
        lessonAdapter = LessonAdapter(list)
        lessonRecyclerView.adapter = lessonAdapter
    }

    fun setProductPrice(price: Float, productActive: Boolean, productAvailable: Boolean?) {
        if (productActive && productAvailable != null && !productAvailable && price != 0f) {
            bPrice.setVisible(true)
            bPrice.text = resources.getString(R.string.price_buy, price)
        } else {
            bPrice.setVisible(false)
        }
    }

    fun setProductName(name: String) {
        tvName.text = name
    }
}