package com.musicabinet.mobile.ui.home.tutorial

import com.musicabinet.mobile.model.home.HomeDataElement

/**
 * @author Kirchhoff-
 */
interface HomeTutorialContract {

    interface View {

        fun showLoading(visible: Boolean)

        fun disablePaginationLoading()

        fun showHomeTutorialList(visible: Boolean)

        fun showHomeTutorialError()

        fun openTutorial(url: String, title: String)

        fun setHomeTutorialItem(videoList: List<HomeDataElement>, enablePagination: Boolean)
    }

    interface Presenter {

        fun loadItems()

        fun onTutorialClick(tutorialItem: HomeDataElement)

        fun unsubscribe()

    }
}