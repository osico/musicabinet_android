package com.musicabinet.mobile.ui.home.video

import com.musicabinet.mobile.model.home.HomeDataElement

/**
 * @author Kirchhoff-
 */
interface HomeVideoContract {

    interface View {

        fun showLoading(visible: Boolean)

        fun showPaginationLoading(visible: Boolean)

        fun showHomeVideoList(visible: Boolean)

        fun showHomeVideoError()

        fun setHomeVideoItem(videoList: List<HomeDataElement>)
    }

    interface Presenter {

        fun loadItems()

    }
}