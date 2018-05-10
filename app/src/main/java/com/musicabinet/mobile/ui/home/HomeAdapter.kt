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

    private lateinit var homeNewsView: HomeNewsView
    private lateinit var homeTutorialView: HomeTutorialView

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return if (position == 0) {
            homeNewsView = HomeNewsView(context)
            container.addView(homeNewsView)
            homeNewsView
        } else {
            homeTutorialView = HomeTutorialView(context)
            container.addView(homeTutorialView)
            homeTutorialView
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
