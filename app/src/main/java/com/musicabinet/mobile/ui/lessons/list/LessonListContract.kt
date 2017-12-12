package com.musicabinet.mobile.ui.lessons.list

import android.content.Intent
import com.musicabinet.mobile.model.instrument.matrix.local.InstrumentGroup
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

        fun moveToPaymentScreen(id: String, requestCode: Int)

        fun moveToLesson(id: String)

        fun showSuccessPayment()
    }

    interface Presenter {

        fun getFilters(instrumentId: String)

        fun buyLesson(lessonId: String)

        fun onLessonClick(item: InstrumentGroup)

        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    }
}