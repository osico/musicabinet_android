package com.musicabinet.mobile.ui.lessons.list

import com.musicabinet.mobile.model.instrument.matrix.LessonItem
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

        fun showAuthorizedError()

        fun showBuyLoading(show: Boolean)

        fun showPaymentDialog()

        fun moveToLesson(id: String)

        fun showSuccessPayment()

        fun showNotAvailableError()
    }

    interface Presenter {

        fun getFilters(instrumentId: String)

        fun buyLesson(lessonId: String)

        fun onLessonClick(item: LessonItem)
    }
}