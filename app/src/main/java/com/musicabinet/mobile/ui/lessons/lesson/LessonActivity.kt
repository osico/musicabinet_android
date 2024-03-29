package com.musicabinet.mobile.ui.lessons.lesson

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.ViewGroup
import android.widget.PopupWindow
import com.musicabinet.mobile.Injection
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.setTint
import com.musicabinet.mobile.extensions.setVisible
import com.musicabinet.mobile.extensions.toast
import com.musicabinet.mobile.model.lesson.lesson.Lesson
import com.musicabinet.mobile.model.lesson.local.LessonData
import com.musicabinet.mobile.model.lesson.local.MethodItem
import com.musicabinet.mobile.model.lesson.remote.Accompaniment
import com.musicabinet.mobile.model.lesson.remote.Stave
import com.musicabinet.mobile.ui.lessons.lesson.dialog.LessonSelectActivity
import com.musicabinet.mobile.ui.view.metronome.MetronomeView
import kotlinx.android.synthetic.main.activity_lesson.*
/**
 * @author Kirchhoff-
 */
class LessonActivity : AppCompatActivity(), LessonContract.View, MetronomeView.OnMetronomeStatusChange {

    companion object {
        const val LESSON_ID_ARG = "LESSON_ID_ARG"
    }

    private lateinit var presenter: LessonContract.Presenter
    private var metronomePopup: PopupWindow? = null
    //Can be lateinit, but looks like bug in build tools
    private var adapter: LessonAdapter? = null
    private lateinit var metronomeView: MetronomeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)

        presenter = LessonPresenter(this, Injection.provideRepository())
        presenter.getLessonGroup(intent.getStringExtra(LESSON_ID_ARG))
        presenter.getLessonInformation(intent.getStringExtra(LESSON_ID_ARG))

        tvLesson.setOnClickListener { presenter.selectLessonClick() }
        ivBack.setOnClickListener { onBackPressed() }
        ivMetronome.setOnClickListener {
            if (metronomePopup == null || !metronomePopup!!.isShowing)
                showMetronomePopup()
            else
                metronomePopup!!.dismiss()
        }

        metronomeView = MetronomeView(this)
        metronomeView.setOnMetronomeStatusChange(this)

        tabLayout.setupWithViewPager(viewPager)
    }

    override fun showSuccess() {
        toast(R.string.success)
    }

    override fun showError() {
        toast(R.string.internal_error)
    }

    override fun showSelectLesson(lessonList: ArrayList<Lesson>, lessonId: String, requestCode: Int,
                                  resultId: String, resultName: String) {
        val intent = Intent(this, LessonSelectActivity::class.java)
        intent.putParcelableArrayListExtra(LessonSelectActivity.LESSON_LIST_ARG, lessonList)
        intent.putExtra(LessonSelectActivity.LESSON_ID_ARG, lessonId)
        intent.putExtra(LessonSelectActivity.LESSON_ID_RESULT_ARG, resultId)
        intent.putExtra(LessonSelectActivity.LESSON_NAME_RESULT_ARG, resultName)
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.onActivityResult(requestCode, resultCode, data)
        adapter?.onActivityResult(requestCode, resultCode, data)
    }

    override fun showLoading(show: Boolean) {
        progressBar.setVisible(show)
    }

    override fun onLessonSelected(id: String) {
        presenter.getPreparedLesson(id)
    }

    override fun showMethod(methodList: List<MethodItem>) {
        adapter?.setMethodList(methodList)
    }

    override fun showLessonImages(lessonImagesList: List<LessonData>) {
        adapter = LessonAdapter(this, false)
        viewPager.adapter = adapter
        adapter?.showLessonImagesList(lessonImagesList)
    }

    override fun showLessonTitle(title: String) {
        tvLesson.text = title
    }

    override fun setLessonTime(time: Long, lessonId: String) {
        timerView.setStartTime(time, lessonId)
    }

    override fun showAccompaniments(accompaniments: MutableSet<Accompaniment>) {
        adapter?.setAccompaniments(accompaniments)
    }

    override fun showGuideMachine(stave: Stave?, userStaveId: Boolean) {
        adapter = LessonAdapter(this, true)
        viewPager.adapter = adapter
        adapter?.setStave(stave, userStaveId)
    }

    override fun onPause() {
        metronomeView.stopTick()
        adapter?.onPause()
        timerView.onPause()
        presenter.unsubscribe()
        super.onPause()
    }

    override fun metronomeStatusChange(enable: Boolean) {
        if (enable) {
            ivMetronome.setTint(R.color.red)
        } else {
            ivMetronome.setTint(R.color.white)
        }
    }

    override fun onMetronomeBackgroundClick() {
        metronomePopup!!.dismiss()
    }

    private fun showMetronomePopup() {
        metronomePopup = PopupWindow(metronomeView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT, true)
        metronomePopup!!.animationStyle = android.R.style.Animation_Dialog
        metronomePopup!!.isOutsideTouchable
        metronomePopup!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        metronomePopup!!.showAtLocation(ivMetronome, Gravity.END,
                resources.getDimensionPixelSize(R.dimen.metronome_popup_x_margin),
                -resources.getDimensionPixelSize(R.dimen.metronome_popup_y_margin))
    }
}