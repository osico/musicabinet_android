package com.musicabinet.mobile.ui.cabinet.email

/**
 * @author Kirchhoff-
 */
interface EnterEmailContract {

    interface View {

        fun enableNextButton(enable: Boolean)

    }

    interface Presenter {

        fun onUserTypeEmail(email: String)
    }
}