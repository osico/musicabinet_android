package com.musicabinet.mobile.ui.home.tools

import com.musicabinet.mobile.model.home.HomeToolsElement

/**
 * @author Kirchhoff-
 */
class HomeToolsPresenter(private val view: HomeToolsContract.View) : HomeToolsContract.Presenter {

    override fun subscribe() {
        view.showTools(listOf(GYM_ITEM, REM_ITEM, SRM_ITEM))
    }

    companion object {
        private val GYM_ITEM = HomeToolsElement("GYM", "Technique excercises", "dd033233-1bb2-4467-8083-af6045254132")
        private val REM_ITEM = HomeToolsElement("REM", "Rhythm excercise machine", "72dd022f-2b63-40a7-8b16-3fb9b556597f")
        private val SRM_ITEM = HomeToolsElement("SRM", "Sight reading machine", "e39b759a-33dc-4ee4-8541-8fc27b9a3fcf")
    }

    override fun onToolsClick(item: HomeToolsElement) {
        view.openTools(item.id)
    }
}