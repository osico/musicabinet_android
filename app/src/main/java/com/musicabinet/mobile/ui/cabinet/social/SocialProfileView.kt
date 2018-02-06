package com.musicabinet.mobile.ui.cabinet.social

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.musicabinet.mobile.R
import com.musicabinet.mobile.ui.cabinet.CabinetActivity
import com.musicabinet.mobile.ui.cabinet.social.web.WebAuthorizationActivity
import kotlinx.android.synthetic.main.view_social_profile.view.*

/**
 * @author Kirchhoff-
 */
class SocialProfileView : LinearLayout {


    companion object {
        const val FACEBOOK_URL = "https://www.facebook.com/dialog/oauth?client_id=1845606962375917&redirect_uri=https%3a%2f%2fapi.musicabinet.com%2fplatform%2foauth%2fFACEBOOK&response_type=code&scope=public_profile,email"
        const val LINKED_IN_URL = "https://www.linkedin.com/oauth/v2/authorization?response_type=code&client_id=78qkahnfqw17u8&redirect_uri=https%3a%2f%2fapi.musicabinet.com%2fplatform%2foauth%2fLINKEDIN&state=987654321&scope=r_basicprofile%20r_emailaddress"
        const val INSTAGRAM_URL = "https://api.instagram.com/oauth/authorize/?client_id=35f9b7f5fe1541fa84e43430140befec&response_type=code&redirect_uri=https%3a%2f%2fapi.musicabinet.com%2fplatform%2foauth%2fINSTAGRAM"
    }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    fun init() {
        LayoutInflater.from(context).inflate(R.layout.view_social_profile, this, true)

        bFacebook.setOnClickListener {
            WebAuthorizationActivity.startWebAuthorization(context as Activity, CabinetActivity.REQUEST_USER_LOGIN, FACEBOOK_URL)
        }

        bLinkedId.setOnClickListener {
            WebAuthorizationActivity.startWebAuthorization(context as Activity, CabinetActivity.REQUEST_USER_LOGIN, LINKED_IN_URL)
        }

        bInstagram.setOnClickListener {
            WebAuthorizationActivity.startWebAuthorization(context as Activity, CabinetActivity.REQUEST_USER_LOGIN, INSTAGRAM_URL)
        }
    }
}