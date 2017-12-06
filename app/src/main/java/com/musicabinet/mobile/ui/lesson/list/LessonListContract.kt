package com.musicabinet.mobile.ui.lesson.list

import com.musicabinet.mobile.model.instrument.matrix.local.InstrumentLessonList

/**
 * @author Kirchhoff-
 */
interface LessonListContract {

    interface View {

        fun showLoading(visible: Boolean)

        fun showLessonFilter(list: List<InstrumentLessonList>)

        fun showError()

        fun showSuccess()

        fun showBuyLoading(show: Boolean)
    }

    interface Presenter {

        fun getFilters(instrumentId: String)

        fun buyLesson(lessonId: String)
    }
}