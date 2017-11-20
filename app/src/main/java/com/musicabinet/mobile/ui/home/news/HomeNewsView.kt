package com.musicabinet.mobile.ui.home.news

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.musicabinet.mobile.Injection
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.setVisible
import com.musicabinet.mobile.model.home.HomeDataElement
import com.musicabinet.mobile.ui.home.news.adapter.HomeNewsAdapter
import kotlinx.android.synthetic.main.view_home_news.view.*

/**
 * @author Kirchhoff-
 */
class HomeNewsView : FrameLayout, HomeNewsContract.View {

    private var homeVideoAdapter: HomeNewsAdapter? = null
    private lateinit var presenter: HomeNewsContract.Presenter

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
        LayoutInflater.from(context).inflate(R.layout.view_home_news, this, true)

        presenter = HomeNewsPresenter(Injection.provideRepository(), this)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

        recyclerView.setVisible(false)
        cvError.setVisible(false)
        progressBar.setVisible(true)
        // presenter.loadItems()
    }

    override fun showLoading(visible: Boolean) {
        progressBar.setVisible(visible)
    }

    override fun showPaginationLoading(visible: Boolean) {

    }

    override fun showHomeNewsError() {
        cvError.setVisible(true)
    }

    override fun showHomeNewsList(visible: Boolean) {
        recyclerView.setVisible(visible)
    }

    override fun setHomeNewsItem(videoList: List<HomeDataElement>) {
        if (homeVideoAdapter == null) {
            homeVideoAdapter = HomeNewsAdapter(videoList)
            recyclerView.layoutManager = LinearLayoutManager(context,
                    LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = homeVideoAdapter
        } else {
            homeVideoAdapter?.addItems(videoList)
        }

        recyclerView.setVisible(true)
    }

}