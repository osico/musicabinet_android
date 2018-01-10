package com.musicabinet.mobile.ui.cabinet.password

/**
 * @author Kirchhoff-
 */
interface CabinetPasswordContract {

    interface View {

        fun enableNextButton(enable: Boolean)

        fun showLoading(show: Boolean)

        fun moveToForgotPasswordScreen()

        fun showPasswordError()

        fun showEmailError()

        fun moveToHomeScreen()

    }

    interface Presenter {

        fun onUserType(password: String)

        fun forgotPassword()

        fun loginUser(email: String, password: String)

        fun unsubscribe()
    }
}