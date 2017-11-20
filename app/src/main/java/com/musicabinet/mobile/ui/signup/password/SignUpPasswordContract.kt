package com.musicabinet.mobile.ui.signup.password

/**
 * @author Kirchhoff-
 */
interface SignUpPasswordContract {

    interface View {

        fun enableNextButton(enable: Boolean)

        fun showPasswordMismatchError()

        fun moveToFinishRegistration()
    }

    interface Presenter {

        fun onUserType(password: String, confirmPassword: String)

        fun registerUser(password: String, confirmPassword: String)
    }
}