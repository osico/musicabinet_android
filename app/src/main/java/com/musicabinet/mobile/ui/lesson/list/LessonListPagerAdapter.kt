package com.musicabinet.mobile.ui.lesson.list

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.musicabinet.mobile.model.instrument.matrix.local.InstrumentCourse
import com.musicabinet.mobile.model.instrument.matrix.local.InstrumentLessonList

/**
 * @author Kirchhoff-
 */
class LessonListPagerAdapter(private val context: Context,
                             private val lessonGroup: List<InstrumentLessonList>,
                             private val instrumentCourse: InstrumentCourse) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val lessonListView = LessonListView(context)
        lessonListView.setLessonList(lessonGroup[position].items)
        lessonListView.setProductName(instrumentCourse.name)
        lessonListView.setProductPrice(instrumentCourse.productPrice)
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