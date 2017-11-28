package com.musicabinet.mobile.ui.courses

/**
 * @author Kirchhoff-
 */
interface CoursesContract {

    interface View {

    }

    interface Presenter {

        fun loadInstrumentMatrix(instrumentId: String)

    }
}