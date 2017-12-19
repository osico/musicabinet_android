package com.musicabinet.mobile.ui.lessons.lesson.view.lesson.page

/**
 * @author Kirchhoff-
 */
class LessonPagePresenter(private val view: LessonPageContract.View) : LessonPageContract.Presenter {

    private var currentPage: Int = 1
    private var pagesList: List<List<String>>? = null

    override fun setPageList(list: List<List<String>>) {
        pagesList = list
        if (currentPage == 1 && list.size == 1) {
            view.showNextPageIndicator(false, "")
        } else {
            view.showNextPageIndicator(true, 2.toString())
        }

        view.showPreviousPageIndicator(false, "")
        view.showCurrentPage(currentPage.toString())
    }

    override fun onNextPageClick() {
        currentPage++

        view.showCurrentPage(currentPage.toString())
        if (currentPage < pagesList!!.size) {
            view.showNextPageIndicator(true, (currentPage + 1).toString())
        } else {
            view.showNextPageIndicator(false, "")
        }

        view.showPreviousPageIndicator(true, (currentPage - 1).toString())
    }

    override fun onPreviousPageClick() {
        currentPage--

        view.showCurrentPage(currentPage.toString())
        if (currentPage != 1) {
            view.showPreviousPageIndicator(true, (currentPage - 1).toString())
        } else {
            view.showPreviousPageIndicator(false, "")
        }

        view.showNextPageIndicator(true, (currentPage + 1).toString())
    }

}