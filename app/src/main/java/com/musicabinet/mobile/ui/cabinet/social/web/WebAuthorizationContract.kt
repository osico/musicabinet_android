package com.musicabinet.mobile.ui.cabinet.social.web

/**
 * @author Kirchhoff-
 */
interface WebAuthorizationContract {

    interface View {

        fun showLoading(show: Boolean)

        fun showError()

        fun moveToHomeScreen()

    }

    interface Presenter {

        fun shouldLoadUrl(url: String?): Boolean

        fun unsubscribe()
    }
}