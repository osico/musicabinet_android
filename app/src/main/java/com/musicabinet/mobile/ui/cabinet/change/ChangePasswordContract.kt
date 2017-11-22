package com.musicabinet.mobile.ui.cabinet.change

/**
 * @author Kirchhoff-
 */
interface ChangePasswordContract {

    interface View {

        fun enableSendButton(enable: Boolean)

        fun showError()

        fun showMailError()

        fun moveToChangePasswordSuccessScreen()
    }

    interface Presenter {

        fun onUserType(email: String)

        fun changePassword(email: String)

    }
}