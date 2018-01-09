package com.musicabinet.mobile.ui.cabinet.email

/**
 * @author Kirchhoff-
 */
interface EnterEmailContract {

    interface View {

        fun enableNextButton(enable: Boolean)

        fun moveToEnterPassword()

        fun showMailError()
    }

    interface Presenter {

        fun onUserTypeEmail(email: String)

        fun onNextClick(mail: String)
    }
}