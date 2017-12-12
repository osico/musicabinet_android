package com.musicabinet.mobile.ui.lessons.lesson

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import com.musicabinet.mobile.Injection
import com.musicabinet.mobile.R
import com.musicabinet.mobile.model.lesson.Lesson
import com.musicabinet.mobile.ui.lessons.lesson.dialog.LessonSelectActivity
import kotlinx.android.synthetic.main.toolbar_lesson.*
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)

        supportActionBar?.setCustomView(R.layout.toolbar_lesson)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM

        presenter = LessonPresenter(this, Injection.provideRepository())
        presenter.getLessonGroup(intent.getStringExtra(LESSON_ID_ARG))

        tvLesson.setOnClickListener { presenter.selectLessonClick() }
    }

    override fun showSuccess() {
        toast("Success")
    }

    override fun showError() {
        toast("Error")
    }

    override fun showLessonsDialog(lessonList: List<Lesson>) {
        val intent = Intent(this, LessonSelectActivity::class.java)
        intent.putExtra(LessonSelectActivity.LESSON_LIST_ARG, lessonList as Serializable)
        startActivity(intent)
    }
}