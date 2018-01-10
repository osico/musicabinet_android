package com.musicabinet.mobile.ui.courses

import com.musicabinet.mobile.model.instrument.matrix.local.InstrumentCourse

/**
 * @author Kirchhoff-
 */
interface CoursesContract {

    interface View {

        fun showLoading(visible: Boolean)

        fun showCourseError(visible: Boolean)

        fun showCourseList(visible: Boolean)

        fun showBuyButton(visible: Boolean)

        fun showCourses(list: List<InstrumentCourse>)

        fun showPaymentDialog()
    }

    interface Presenter {

        fun loadInstrumentMatrix(instrumentId: String)

        fun onBuyClick()

        fun unsubscribe()

    }
}