package com.musicabinet.mobile.ui.courses

import com.musicabinet.mobile.repository.MusicabinetRepository

/**
 * @author Kirchhoff-
 */
class CoursesPresenter(private val view: CoursesContract.View,
                       private val repository: MusicabinetRepository) : CoursesContract.Presenter {


    override fun loadInstrumentMatrix(instrumentId: String) {

    }

}