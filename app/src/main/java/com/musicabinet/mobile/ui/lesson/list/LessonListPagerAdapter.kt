package com.musicabinet.mobile.ui.lesson.list

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.musicabinet.mobile.model.instrument.matrix.filter.InstrumentFilterItem

/**
 * @author Kirchhoff-
 */
class LessonListPagerAdapter(private val context: Context,
                             private val lessonGroup: List<InstrumentFilterItem>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val lessonListView = LessonListView(context)
        container.addView(lessonListView)
        return lessonListView
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View)
    }

    override fun getCount() = lessonGroup.size

    override fun isViewFromObject(view: View, `object`: Any) = view === `object`

    override fun getPageTitle(position: Int) = lessonGroup[position].name
}