package com.musicabinet.mobile.ui.home.news

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.musicabinet.mobile.Injection
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.setVisible
import com.musicabinet.mobile.model.home.HomeDataElement
import com.musicabinet.mobile.ui.home.HomeItemAdapter
import com.musicabinet.mobile.ui.web.WebVideoActivity
import com.musicabinet.mobile.utils.BaseRecyclerAdapter
import kotlinx.android.synthetic.main.view_home_news.view.*

/**
 * @author Kirchhoff-
 */
class HomeNewsView : FrameLayout, HomeNewsContract.View, BaseRecyclerAdapter.OnItemClickListener<HomeDataElement> {

    private var homeNewsAdapter: HomeItemAdapter? = null
    private lateinit var presenter: HomeNewsContract.Presenter
    private var loading: Boolean = false
    private val linearLayoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL, false)

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
        cvLoading.setVisible(true)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!loading && linearLayoutManager.itemCount
                        <= (linearLayoutManager.findLastVisibleItemPosition() + 1)) {
                    loading = true
                    presenter.loadItems()
                }
            }
        })
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        presenter.loadItems()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.unsubscribe()
    }

    override fun showLoading(visible: Boolean) {
        cvLoading.setVisible(visible)
    }

    override fun disablePaginationLoading() {
        homeNewsAdapter?.disablePaginationLoading()
    }

    override fun showHomeNewsError() {
        cvError.setVisible(true)
    }

    override fun showHomeNewsList(visible: Boolean) {
        recyclerView.setVisible(visible)
    }

    override fun setHomeNewsItem(videoList: List<HomeDataElement>, enablePagination: Boolean) {
        if (homeNewsAdapter == null) {
            homeNewsAdapter = HomeItemAdapter(videoList, R.layout.item_home_news)
            recyclerView.layoutManager = linearLayoutManager
            recyclerView.adapter = homeNewsAdapter
        } else {
            homeNewsAdapter?.addItems(videoList)
            loading = false
        }

        homeNewsAdapter?.setOnItemClickListener(this)
        recyclerView.setVisible(true)
    }

    override fun onItemClick(item: HomeDataElement) {
        presenter.onNewsClick(item)
    }

    override fun openNews(url: String, title: String) {
        WebVideoActivity.startWebVideo(context, url, title)
    }
}