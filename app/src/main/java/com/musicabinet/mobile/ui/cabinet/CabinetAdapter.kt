package com.musicabinet.mobile.ui.cabinet

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.musicabinet.mobile.R
import com.musicabinet.mobile.ui.cabinet.email.EnterEmailView
import com.musicabinet.mobile.ui.cabinet.social.SocialProfileView

/**
 * @author Kirchhoff-
 */
class CabinetAdapter(private val context: Context) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return if (position == 0) {
            val emailView = EnterEmailView(context)
            container.addView(emailView)
            emailView
        } else {
            val socialView = SocialProfileView(context)
            container.addView(socialView)
            socialView
        }
    }

    override fun isViewFromObject(view: View, `object`: Any) = view === `object`

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0)
            context.getString(R.string.email_tab)
        else
            context.getString(R.string.social_profile_tab)
    }

    override fun getCount() = 2
}