package com.musicabinet.mobile.ui.home.news

import com.musicabinet.mobile.model.home.HomeDataElement

/**
 * @author Kirchhoff-
 */
interface HomeNewsContract {

    interface View {

        fun showLoading(visible: Boolean)

        fun showPaginationLoading(visible: Boolean)

        fun showHomeNewsList(visible: Boolean)

        fun showHomeNewsError()

        fun setHomeNewsItem(videoList: List<HomeDataElement>)
    }

    interface Presenter {

        fun loadItems()

    }
}