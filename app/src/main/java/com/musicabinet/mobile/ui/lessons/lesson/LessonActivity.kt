package com.musicabinet.mobile.ui.lessons.lesson

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.musicabinet.mobile.Injection
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.setVisible
import com.musicabinet.mobile.model.lesson.lesson.Lesson
import com.musicabinet.mobile.model.lesson.local.LessonData
import com.musicabinet.mobile.model.lesson.local.MethodItem
import com.musicabinet.mobile.ui.lessons.lesson.dialog.LessonSelectActivity
import kotlinx.android.synthetic.main.activity_lesson.*
import org.jetbrains.anko.toast
import java.io.Serializable

/**
 * @author Kirchhoff-
 */
class LessonActivity : AppCompatActivity(), LessonContract.View {

    companion object {
        const val LESSON_ID_ARG = "LESSON_ID_ARG"
    }

    private lateinit var presenter: LessonContract.Presenter
    private val adapter = LessonAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)

        presenter = LessonPresenter(this, Injection.provideRepository())
        presenter.getLessonGroup(intent.getStringExtra(LESSON_ID_ARG))
        presenter.getLessonInformation(intent.getStringExtra(LESSON_ID_ARG))

        tvLesson.setOnClickListener { presenter.selectLessonClick() }
        ivBack.setOnClickListener { onBackPressed() }
    }

    override fun showSuccess() {
        toast("Success")
    }

    override fun showError() {
        toast("Error")
    }

    override fun showSelectLesson(lessonList: List<Lesson>, requestCode: Int,
                                  resultId: String, resultName: String) {
        val intent = Intent(this, LessonSelectActivity::class.java)
        intent.putExtra(LessonSelectActivity.LESSON_LIST_ARG, lessonList as Serializable)
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.onActivityResult(requestCode, resultCode, data)
    }

    override fun showLoading(show: Boolean) {
        progressBar.setVisible(show)
    }

    override fun onLessonSelected() {
        toast("On LessonSelected")
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

}