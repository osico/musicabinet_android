package com.musicabinet.mobile.ui.lessons.lesson.view.method

import com.musicabinet.mobile.model.lesson.local.MethodItem

/**
 * @author Kirchhoff-
 */
interface MethodViewContract {

    interface View {

        fun openVideo(url: String, title: String)

        fun openInformation(information: String)
    }

    interface Presenter {

        fun onVideoClick(methodItem: MethodItem)

        fun onInfoClick(information: String)
    }
}