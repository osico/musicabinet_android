package com.musicabinet.mobile.ui.cabinet.social

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.musicabinet.mobile.R
import com.musicabinet.mobile.ui.cabinet.social.web.WebAuthorizationActivity
import kotlinx.android.synthetic.main.view_social_profile.view.*

/**
 * @author Kirchhoff-
 */
class SocialProfileView : LinearLayout {
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
            val intent = Intent(context, WebAuthorizationActivity::class.java)
            context.startActivity(intent)
        }

        bGoogle.setOnClickListener {
            val intent = Intent(context, WebAuthorizationActivity::class.java)
            context.startActivity(intent)
        }
    }
}