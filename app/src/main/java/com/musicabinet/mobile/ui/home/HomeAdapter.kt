package com.musicabinet.mobile.ui.home

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup

import com.musicabinet.mobile.R
import com.musicabinet.mobile.ui.home.news.HomeNewsView
import com.musicabinet.mobile.ui.home.tutorial.HomeTutorialView

/**
 * @author Kirchhoff-
 */

class HomeAdapter(private val context: Context) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        if (position == 0) {
            val homeNewsView = HomeNewsView(context)
            container.addView(homeNewsView)
            return homeNewsView
        } else {
            val homeTutorialView = HomeTutorialView(context)
            container.addView(homeTutorialView)
            return homeTutorialView
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View)
    }

    override fun getCount() = 2

    override fun isViewFromObject(view: View, `object`: Any) = view === `object`
    
    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0)
            context.getString(R.string.news_tab)
        else
            context.getString(R.string.tutorial_tab)
    }
}
