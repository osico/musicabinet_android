package com.musicabinet.mobile.ui.lessons.lesson

import android.content.Context
import android.content.Intent
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.musicabinet.mobile.R
import com.musicabinet.mobile.model.lesson.local.LessonData
import com.musicabinet.mobile.model.lesson.local.MethodItem
import com.musicabinet.mobile.model.lesson.remote.Accompaniment
import com.musicabinet.mobile.model.lesson.remote.Stave
import com.musicabinet.mobile.ui.lessons.lesson.view.guide.machine.GuideMachineView
import com.musicabinet.mobile.ui.lessons.lesson.view.lesson.LessonView
import com.musicabinet.mobile.ui.lessons.lesson.view.method.MethodView

/**
 * @author Kirchhoff-
 */
class LessonAdapter(private val context: Context, private val hasGuideMachine: Boolean) : PagerAdapter() {

    private var lessonView: LessonView? = null
    private var methodView: MethodView? = null
    private var guideMachineView: GuideMachineView? = null

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        if (position == 0 && hasGuideMachine) {
            guideMachineView = GuideMachineView(context)
            container.addView(guideMachineView)
            return guideMachineView!!
        } else if (position == 0 && !hasGuideMachine) {
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

    fun showLessonImagesList(imagesList: List<LessonData>) {
        lessonView?.setLessonImages(imagesList)
    }

    fun setAccompaniments(accompaniments: Set<Accompaniment>) {
        lessonView?.setAccompaniments(accompaniments)
        guideMachineView?.setAccompaniments(accompaniments)
    }

    fun setStave(stave: Stave?) {
        guideMachineView?.setStave(stave)
    }

    fun onPause() {
        lessonView?.onPause()
        guideMachineView?.onPause()
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        guideMachineView?.onActivityResult(requestCode, resultCode, data)
    }
}