package com.musicabinet.mobile.ui.lessons.lesson.view

import com.musicabinet.mobile.model.lesson.local.MethodItem

/**
 * @author Kirchhoff-
 */
interface MethodViewContract {

    interface View {

        fun openVideo(url: String)
    }

    interface Presenter {

        fun onVideoClick(methodItem: MethodItem)
    }
}