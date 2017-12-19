package com.musicabinet.mobile.ui.lessons.lesson.view.lesson.page

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.setTextFromResource
import com.musicabinet.mobile.extensions.setVisible
import kotlinx.android.synthetic.main.view_lesson_page.view.*

/**
 * @author Kirchhoff-
 */
class LessonPageView : ConstraintLayout, LessonPageContract.View {

    private val presenter = LessonPagePresenter(this)

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
        LayoutInflater.from(context).inflate(R.layout.view_lesson_page, this, true)

        layoutPrevious.setOnClickListener { presenter.onPreviousPageClick() }
        layoutNext.setOnClickListener { presenter.onNextPageClick() }
    }

    override fun showPreviousPageIndicator(show: Boolean, position: String) {
        tvPreviousPage.setTextFromResource(R.string.page, position)
        layoutPrevious.setVisible(show)
    }

    override fun showNextPageIndicator(show: Boolean, position: String) {
        tvNextPage.setTextFromResource(R.string.page, position)
        layoutNext.setVisible(show)
    }

    override fun showCurrentPage(position: String) {
        tvCurrentPage.setTextFromResource(R.string.page, position)
    }

    override fun onNextPageClick() {
        presenter.onNextPageClick()
    }

    override fun onPreviousPageClick() {
        presenter.onPreviousPageClick()
    }

    fun setPageList(list: List<List<String>>) {
        presenter.setPageList(list)
    }

}