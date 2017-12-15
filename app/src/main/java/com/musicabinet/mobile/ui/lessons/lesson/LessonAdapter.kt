package com.musicabinet.mobile.ui.lessons.lesson

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.musicabinet.mobile.R
import com.musicabinet.mobile.model.lesson.local.MethodItem
import com.musicabinet.mobile.ui.lessons.lesson.view.LessonView
import com.musicabinet.mobile.ui.lessons.lesson.view.method.MethodView

/**
 * @author Kirchhoff-
 */
class LessonAdapter(private val context: Context) : PagerAdapter() {

    private var lessonView: LessonView? = null
    private var methodView: MethodView? = null

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        if (position == 0) {
            lessonView = LessonView(context)
            container.addView(lessonView)
            return lessonView!!
        } else {
            methodView = MethodView(context)
            container.addView(methodView)
            return methodView!!
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View)
    }

    override fun getCount() = 2

    override fun isViewFromObject(view: View, `object`: Any) = view === `object`

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0)
            context.getString(R.string.lesson_tab)
        else
            context.getString(R.string.method_tab)
    }

    fun setMethodList(list: List<MethodItem>) {
        methodView?.setMethodList(list)
    }
}