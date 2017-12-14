package com.musicabinet.mobile.ui.lessons.lesson.view

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.musicabinet.mobile.R
import com.musicabinet.mobile.model.lesson.local.MethodItem
import com.musicabinet.mobile.ui.lessons.lesson.view.adapter.MethodViewAdapter
import kotlinx.android.synthetic.main.view_method.view.*

/**
 * @author Kirchhoff-
 */
class MethodView : FrameLayout {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    fun init() {
        LayoutInflater.from(context).inflate(R.layout.view_method, this, true)

        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,
                false)
    }

    public fun setMethodList(methodList: List<MethodItem>) {
        val adapter = MethodViewAdapter(methodList)
        recyclerView.adapter = adapter
    }
}