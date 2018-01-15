package com.musicabinet.mobile.ui.splash

import com.musicabinet.archmvp.base.BaseContract

/**
 * @author Kirchhoff-
 */
interface SplashContract {

    interface View : BaseContract.View {

        fun moveToHome()

    }

    interface Presenter : BaseContract.Presenter<SplashContract.View> {

        fun subscribe()

        fun unsubscribe()
    }
}