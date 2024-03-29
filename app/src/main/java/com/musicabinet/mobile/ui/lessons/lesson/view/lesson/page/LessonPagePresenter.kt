package com.musicabinet.mobile.ui.lessons.lesson.view.lesson.page

import com.musicabinet.mobile.model.lesson.local.LessonData

/**
 * @author Kirchhoff-
 */
class LessonPagePresenter(private val view: LessonPageContract.View) : LessonPageContract.Presenter {

    private var currentPage: Int = 1
    private var pagesList: List<LessonData>? = null
    private var pageClickListener: OnPageClickListener? = null

    fun setOnPageClickListener(listener: OnPageClickListener) {
        pageClickListener = listener
    }

    override fun setPageList(list: List<LessonData>) {
        pagesList = list
        if (currentPage == 1 && list.size == 1) {
            view.showNextPageIndicator(false, "")
        } else {
            view.showNextPageIndicator(true, 2.toString())
        }

        view.showPreviousPageIndicator(false, "")
        view.showCurrentPage(pagesList!!.get(currentPage - 1).name)
    }

    override fun onNextPageClick() {
        currentPage++

        view.showCurrentPage(pagesList!!.get(currentPage - 1).name)
        if (currentPage < pagesList!!.size) {
            view.showNextPageIndicator(true, (currentPage + 1).toString())
        } else {
            view.showNextPageIndicator(false, "")
        }

        view.showPreviousPageIndicator(true, (currentPage - 1).toString())
        pageClickListener?.onPageChange(currentPage)
    }

    override fun onPreviousPageClick() {
        currentPage--

        view.showCurrentPage(pagesList!!.get(currentPage - 1).name)
        if (currentPage != 1) {
            view.showPreviousPageIndicator(true, (currentPage - 1).toString())
        } else {
            view.showPreviousPageIndicator(false, "")
        }

        view.showNextPageIndicator(true, (currentPage + 1).toString())
        pageClickListener?.onPageChange(currentPage)
    }

    override fun setCurrentPage(position: Int) {
        if (position > currentPage)
            onNextPageClick()
        else if (position < currentPage)
            onPreviousPageClick()
    }


    interface OnPageClickListener {

        fun onPageChange(position: Int)
    }

}