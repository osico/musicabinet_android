package com.musicabinet.mobile.ui.lessons.lesson.view.lesson

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.loadLessonImage
import com.musicabinet.mobile.extensions.setVisible
import com.musicabinet.mobile.model.lesson.local.LessonData
import com.musicabinet.mobile.model.lesson.remote.Accompaniment
import com.musicabinet.mobile.ui.lessons.lesson.view.lesson.page.LessonPagePresenter
import kotlinx.android.synthetic.main.view_lesson.view.*

/**
 * @author Kirchhoff-
 */
class LessonView : FrameLayout, LessonPagePresenter.OnPageClickListener {

    private var images: List<LessonData>? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        LayoutInflater.from(context).inflate(R.layout.view_lesson, this, true)
    }


    fun setLessonImages(lessonImages: List<LessonData>) {
        images = lessonImages
        showLessonImages(images!![0].lessonImages)
        topPageView.setPageList(lessonImages)
        bottomPageView.setPageList(lessonImages)
        topPageView.setOnPageClickListener(this)
        bottomPageView.setOnPageClickListener(this)
    }

    override fun onPageChange(position: Int) {
        bottomPageView.setCurrentPage(position)
        topPageView.setCurrentPage(position)
        showLessonImages(images!![position - 1].lessonImages)
    }

    private fun showLessonImages(lessonImages: List<String>) {
        if (lessonImages.isNotEmpty()) {
            ivLesson1.setVisible(true)
            ivLesson1.loadLessonImage(lessonImages[0])
        } else {
            ivLesson1.setVisible(false)
        }

        if (lessonImages.size > 1) {
            ivLesson2.setVisible(true)
            ivLesson2.loadLessonImage(lessonImages[1])
        } else {
            ivLesson2.setVisible(false)
        }

        if (lessonImages.size > 2) {
            ivLesson3.setVisible(true)
            ivLesson3.loadLessonImage(lessonImages[2])
        } else {
            ivLesson3.setVisible(false)
        }

        if (lessonImages.size > 3) {
            ivLesson4.setVisible(true)
            ivLesson4.loadLessonImage(lessonImages[3])
        } else {
            ivLesson4.setVisible(false)
        }

    }

    fun setAccompaniments(accompaniments: MutableSet<Accompaniment>) {
        soundView.setAccompaniments(accompaniments)
    }

    fun onPause() {
        soundView.onPause()
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        soundView.onActivityResult(requestCode, resultCode, data)
    }
}