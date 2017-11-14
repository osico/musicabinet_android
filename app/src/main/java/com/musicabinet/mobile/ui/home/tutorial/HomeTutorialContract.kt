package com.musicabinet.mobile.ui.home.tutorial

import com.musicabinet.mobile.model.home.HomeDataElement

/**
 * @author Kirchhoff-
 */
interface HomeTutorialContract {

    interface View {

        fun showLoading(visible: Boolean)

        fun showPaginationLoading(visible: Boolean)

        fun showHomeTutorialList(visible: Boolean)

        fun showHomeTutorialError()

        fun setHomeTutorialItem(videoList: List<HomeDataElement>)
    }

    interface Presenter {

        fun loadItems()

    }
}