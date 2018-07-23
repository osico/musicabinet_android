package com.musicabinet.mobile.ui.home.tools

import com.musicabinet.mobile.model.home.HomeToolsElement

/**
 * @author Kirchhoff-
 */
interface HomeToolsContract {

    interface View {

        fun showTools(freeToolsList: List<HomeToolsElement>)

        fun openTools(id: String)

        fun openRegistrationScreen()

    }

    interface Presenter {

        fun subscribe()

        fun onToolsClick(item: HomeToolsElement)

    }
}