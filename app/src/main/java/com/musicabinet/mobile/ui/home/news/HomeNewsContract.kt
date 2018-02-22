package com.musicabinet.mobile.ui.home.news

import com.musicabinet.mobile.model.home.HomeDataElement

/**
 * @author Kirchhoff-
 */
interface HomeNewsContract {

    interface View {

        fun showLoading(visible: Boolean)

        fun disablePaginationLoading()

        fun showHomeNewsList(visible: Boolean)

        fun showHomeNewsError()

        fun setHomeNewsItem(videoList: List<HomeDataElement>, enablePagination: Boolean)

        fun openNews(url: String, title: String)
    }

    interface Presenter {

        fun loadItems()

        fun onNewsClick(newsItem: HomeDataElement)

        fun unsubscribe()

    }
}