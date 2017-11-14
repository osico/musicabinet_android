package com.musicabinet.mobile.ui.home.video

import com.musicabinet.mobile.model.home.HomeDataElement

/**
 * @author Kirchhoff-
 */
interface HomeVideoContract {

    interface View {

        fun showLoading(show: Boolean)

        fun showPaginationLoading(show: Boolean)

        fun showHomeVideo(videoList: List<HomeDataElement>)
    }

    interface Presenter {

        fun loadItems()

    }
}