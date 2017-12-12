package com.musicabinet.mobile.ui.lessons.lesson.dialog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Window
import com.musicabinet.mobile.R
import com.musicabinet.mobile.model.lesson.Lesson
import com.musicabinet.mobile.ui.lessons.lesson.dialog.adapter.LessonSelectAdapter
import kotlinx.android.synthetic.main.activity_lesson_select.*

/**
 * @author Kirchhoff-
 */
class LessonSelectActivity : AppCompatActivity() {

    companion object {
        public const val LESSON_LIST_ARG = "LESSON_LIST_ARG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_lesson_select)

        val lessonList: List<Lesson> = intent.getSerializableExtra(LESSON_LIST_ARG) as List<Lesson>
        val adapter = LessonSelectAdapter(lessonList)
        recyclerView.layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
    }

}