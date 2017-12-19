package com.musicabinet.mobile.ui.lessons.lesson.view.lesson

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.loadLessonImage
import com.musicabinet.mobile.extensions.setVisible
import com.musicabinet.mobile.ui.lessons.lesson.view.lesson.page.LessonPagePresenter
import kotlinx.android.synthetic.main.view_lesson.view.*

/**
 * @author Kirchhoff-
 */
class LessonView : FrameLayout, LessonPagePresenter.OnPageClickListener {

    private var images: List<List<String>>? = null

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


    fun setLessonImages(lessonImages: List<List<String>>) {
        images = lessonImages
        showLessonImages(images!![0])
        topPageView.setPageList(lessonImages)
        bottomPageView.setPageList(lessonImages)
        topPageView.setOnPageClickListener(this)
        bottomPageView.setOnPageClickListener(this)
    }

    override fun onNextPageClick(position: Int) {
        showLessonImages(images!![position])
    }

    override fun onPreviousPageClick(position: Int) {
        showLessonImages(images!![position])
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
}