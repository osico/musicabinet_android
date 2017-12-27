package com.musicabinet.mobile.ui.cabinet.social.web

/**
 * @author Kirchhoff-
 */
interface WebAuthorizationContract {

    interface View {

    }

    interface Presenter {

        fun shouldLoadUrl(url: String?): Boolean
    }
}