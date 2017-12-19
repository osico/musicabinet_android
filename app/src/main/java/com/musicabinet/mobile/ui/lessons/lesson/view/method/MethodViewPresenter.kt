package com.musicabinet.mobile.ui.lessons.lesson.view.method

import com.musicabinet.mobile.model.lesson.local.MethodItem

/**
 * @author Kirchhoff-
 */
class MethodViewPresenter(private val view: MethodViewContract.View) : MethodViewContract.Presenter {

    override fun onVideoClick(methodItem: MethodItem) {
        view.openVideo(methodItem.video.meta)
    }

    override fun onInfoClick(information: String) {
        view.openInformation(information)
    }

}