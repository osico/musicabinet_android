package com.musicabinet.mobile.ui.splash

/**
 * @author Kirchhoff-
 */
interface SplashContract {

    interface View {

        fun moveToHome()

    }

    interface Presenter {

        fun subscribe()

        fun unsubscribe()
    }
}