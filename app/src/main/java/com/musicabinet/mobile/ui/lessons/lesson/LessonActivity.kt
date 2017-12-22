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
import com.musicabinet.mobile.model.lesson.lesson.Lesson
import com.musicabinet.mobile.model.lesson.local.LessonData
import com.musicabinet.mobile.model.lesson.local.MethodItem
import com.musicabinet.mobile.ui.lessons.lesson.dialog.LessonSelectActivity
import com.musicabinet.mobile.ui.view.metronome.MetronomeView
import kotlinx.android.synthetic.main.activity_lesson.*
import org.jetbrains.anko.toast
import java.io.Serializable

/**
 * @author Kirchhoff-
 */
class LessonActivity : AppCompatActivity(), LessonContract.View, MetronomeView.OnMetronomeStatusChange {

    companion object {
        const val LESSON_ID_ARG = "LESSON_ID_ARG"
    }

    private lateinit var presenter: LessonContract.Presenter
    private val adapter = LessonAdapter(this)
    private var metronomePopup: PopupWindow? = null
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

        appBar.addOnOffsetChangedListener { _, _ ->
            if (metronomePopup != null && metronomePopup!!.isShowing)
                metronomePopup!!.dismiss()
        }

        metronomeView = MetronomeView(this)
        metronomeView.setOnMetronomeStatusChange(this)
    }

    override fun showSuccess() {
        toast("Success")
    }

    override fun showError() {
        toast("Error")
    }

    override fun showSelectLesson(lessonList: List<Lesson>, lessonId: String, requestCode: Int,
                                  resultId: String, resultName: String) {
        val intent = Intent(this, LessonSelectActivity::class.java)
        intent.putExtra(LessonSelectActivity.LESSON_LIST_ARG, lessonList as Serializable)
        intent.putExtra(LessonSelectActivity.LESSON_ID_ARG, lessonId)
        intent.putExtra(LessonSelectActivity.LESSON_ID_RESULT_ARG, resultId)
        intent.putExtra(LessonSelectActivity.LESSON_NAME_RESULT_ARG, resultName)
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.onActivityResult(requestCode, resultCode, data)
    }

    override fun showLoading(show: Boolean) {
        progressBar.setVisible(show)
    }

    override fun onLessonSelected(id: String) {
        presenter.getPreparedLesson(id)
    }

    override fun showMethod(methodList: List<MethodItem>) {
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        adapter.setMethodList(methodList)
    }

    override fun showLessonImages(lessonImagesList: List<LessonData>) {
        adapter.showLessonImagesList(lessonImagesList)
    }

    override fun showLessonTitle(title: String) {
        tvLesson.text = title
    }

    override fun onPause() {
        super.onPause()
        metronomeView.stopTick()
    }

    override fun metronomeStatusChange(enable: Boolean) {
        if (enable) {
            ivMetronome.setTint(R.color.red)
        } else {
            ivMetronome.setTint(R.color.white)
        }
    }

    private fun showMetronomePopup() {
        metronomePopup = PopupWindow(metronomeView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true)
        metronomePopup!!.animationStyle = android.R.style.Animation_Dialog
        metronomePopup!!.isOutsideTouchable
        metronomePopup!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        metronomePopup!!.showAtLocation(ivMetronome, Gravity.END,
                resources.getDimensionPixelSize(R.dimen.metronome_popup_x_margin),
                -resources.getDimensionPixelSize(R.dimen.metronome_popup_y_margin))
    }
}