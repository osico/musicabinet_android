package com.musicabinet.mobile.ui.lessons.lesson.view.lesson.page

import com.musicabinet.mobile.model.lesson.local.LessonData

/**
 * @author Kirchhoff-
 */
interface LessonPageContract {

    interface View {

        fun showPreviousPageIndicator(show: Boolean, position: String)

        fun showNextPageIndicator(show: Boolean, position: String)

        fun showCurrentPage(position: String)

        fun setCurrentPage(position: Int)

    }

    interface Presenter {

        fun setPageList(list: List<LessonData>)

        fun setCurrentPage(position: Int)

        fun onNextPageClick()

        fun onPreviousPageClick()

    }

}