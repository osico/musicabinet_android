package com.musicabinet.mobile.ui.home.tools

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.musicabinet.mobile.R
import com.musicabinet.mobile.model.home.HomeToolsElement
import com.musicabinet.mobile.ui.lessons.lesson.LessonActivity
import com.musicabinet.mobile.utils.BaseRecyclerAdapter
import kotlinx.android.synthetic.main.view_home_tools.view.*

/**
 * @author Kirchhoff-
 */
class HomeToolsView : FrameLayout, HomeToolsContract.View, BaseRecyclerAdapter.OnItemClickListener<HomeToolsElement> {

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
        adapter.setOnItemClickListener(this)
        snapHelper.attachToRecyclerView(recyclerView)
        recyclerView.adapter = adapter
    }

    override fun onItemClick(item: HomeToolsElement) {
        presenter.onToolsClick(item)
    }

    override fun openTools(id: String) {
        val intent = Intent(context, LessonActivity::class.java)
        intent.putExtra(LessonActivity.LESSON_ID_ARG, id)
        context.startActivity(intent)
    }
}