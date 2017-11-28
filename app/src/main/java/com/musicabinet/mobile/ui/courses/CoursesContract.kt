package com.musicabinet.mobile.ui.courses

import com.musicabinet.mobile.model.instrument.matrix.local.InstrumentCourse

/**
 * @author Kirchhoff-
 */
interface CoursesContract {

    interface View {

        fun showSuccess(list: List<InstrumentCourse>)

        fun showError()
    }

    interface Presenter {

        fun loadInstrumentMatrix(instrumentId: String)

    }
}