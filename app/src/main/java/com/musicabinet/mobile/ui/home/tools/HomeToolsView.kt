package com.musicabinet.mobile.ui.home.tools

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.musicabinet.mobile.R
import com.musicabinet.mobile.model.home.HomeToolsElement
import kotlinx.android.synthetic.main.view_home_tools.view.*

/**
 * @author Kirchhoff-
 */
class HomeToolsView : FrameLayout, HomeToolsContract.View {

    private lateinit var presenter: HomeToolsContract.Presenter

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
        LayoutInflater.from(context).inflate(R.layout.view_home_tools, this, true)

        presenter = HomeToolsPresenter(this)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        presenter.subscribe()
    }

    override fun showTools(freeToolsList: List<HomeToolsElement>) {
        val adapter = HomeToolsAdapter(freeToolsList)
        recyclerView.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
        recyclerView.adapter = adapter
    }

    override fun openTools(id: String) {
        //Empty for now
    }
}