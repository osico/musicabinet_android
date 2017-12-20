package com.musicabinet.mobile.ui.lessons.lesson.view.lesson.page

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.setTextFromResource
import com.musicabinet.mobile.model.lesson.local.LessonData
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
        if (show)
            layoutPrevious.visibility = View.VISIBLE
        else
            layoutPrevious.visibility = View.INVISIBLE
    }

    override fun showNextPageIndicator(show: Boolean, position: String) {
        tvNextPage.setTextFromResource(R.string.page, position)
        if (show)
            layoutNext.visibility = View.VISIBLE
        else
            layoutNext.visibility = View.INVISIBLE
    }

    override fun showCurrentPage(position: String) {
        tvCurrentPage.text = position
    }

    fun setPageList(list: List<LessonData>) {
        presenter.setPageList(list)
    }

    fun setOnPageClickListener(listener: LessonPagePresenter.OnPageClickListener) {
        presenter.setOnPageClickListener(listener)
    }

    override fun setCurrentPage(position: Int) {
        presenter.setCurrentPage(position)
    }

}