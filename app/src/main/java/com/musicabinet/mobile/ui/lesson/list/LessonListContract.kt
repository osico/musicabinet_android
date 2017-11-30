package com.musicabinet.mobile.ui.lesson.list

import com.musicabinet.mobile.model.instrument.matrix.filter.InstrumentFilterItem

/**
 * @author Kirchhoff-
 */
interface LessonListContract {

    interface View {

        fun showLoading(visible: Boolean)

        fun showLessonFilter(list: List<InstrumentFilterItem>)

        fun showError()
    }

    interface Presenter {

        fun getFilters(instrumentId: String)

    }
}