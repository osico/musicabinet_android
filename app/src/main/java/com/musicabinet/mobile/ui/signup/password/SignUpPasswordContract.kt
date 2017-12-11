package com.musicabinet.mobile.ui.signup.password

/**
 * @author Kirchhoff-
 */
interface SignUpPasswordContract {

    interface View {

        fun enableNextButton(enable: Boolean)

        fun showPasswordMismatchError()

        fun moveToFinishRegistration()

        fun showLoading(show: Boolean)

        fun showError()
    }

    interface Presenter {

        fun onUserType(password: String, confirmPassword: String)

        fun registerUser(email: String, name: String, surname: String, password: String,
                         confirmPassword: String)
    }
}