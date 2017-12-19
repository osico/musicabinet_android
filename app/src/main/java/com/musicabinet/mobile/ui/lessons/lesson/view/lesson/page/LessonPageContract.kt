package com.musicabinet.mobile.ui.lessons.lesson.view.lesson.page

/**
 * @author Kirchhoff-
 */
interface LessonPageContract {

    interface View {

        fun showPreviousPageIndicator(show: Boolean, position: String)

        fun showNextPageIndicator(show: Boolean, position: String)

        fun showCurrentPage(position: String)

        fun onNextPageClick()

        fun onPreviousPageClick()

    }

    interface Presenter {

        fun setPageList(list: List<List<String>>)

        fun onNextPageClick()

        fun onPreviousPageClick()

    }

}