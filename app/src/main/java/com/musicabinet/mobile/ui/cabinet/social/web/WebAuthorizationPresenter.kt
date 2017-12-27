package com.musicabinet.mobile.ui.cabinet.social.web

/**
 * @author Kirchhoff-
 */
class WebAuthorizationPresenter(private val view: WebAuthorizationContract.View)
    : WebAuthorizationContract.Presenter {

    companion object {
        const val MUSICABINET_ENDPOINT = "https://app.musicabinet.com"
    }

    override fun shouldLoadUrl(url: String?): Boolean {
        if (url != null && url.startsWith(MUSICABINET_ENDPOINT))
            return false

        return true
    }

}