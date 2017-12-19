package com.musicabinet.mobile.ui.lessons.lesson.view.method

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Toast
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.openVideoIntent
import com.musicabinet.mobile.model.lesson.local.MethodItem
import com.musicabinet.mobile.ui.lessons.lesson.view.adapter.MethodViewAdapter
import com.musicabinet.mobile.ui.lessons.lesson.view.adapter.MethodViewHolder
import kotlinx.android.synthetic.main.view_method.view.*

/**
 * @author Kirchhoff-
 */
class MethodView : FrameLayout, MethodViewContract.View, MethodViewHolder.OnMethodItemClickListener {

    private val presenter = MethodViewPresenter(this)

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

    fun setMethodList(methodList: List<MethodItem>) {
        val adapter = MethodViewAdapter(methodList, this)
        recyclerView.adapter = adapter
    }

    override fun onInfoClick(information: String) {
        presenter.onInfoClick(information)
    }

    override fun openInformation(information: String) {
        Toast.makeText(context, "OnInformationClick", Toast.LENGTH_SHORT).show()
    }

    override fun onVideoClick(methodItem: MethodItem) {
        presenter.onVideoClick(methodItem)
    }

    override fun openVideo(url: String) {
        context.openVideoIntent(url)
    }
}