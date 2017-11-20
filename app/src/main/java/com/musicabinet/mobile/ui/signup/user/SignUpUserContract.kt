package com.musicabinet.mobile.ui.signup.user

/**
 * @author Kirchhoff-
 */
interface SignUpUserContract {

    interface View {

        fun showEmptyNameError()

        fun showEmptySurnameError()

        fun showEmptyMailError()

        fun showMailError()

        fun enableNextButton(enable: Boolean)

        fun moveToSetPassword()
    }

    interface Presenter {

        fun onUserType(name: String, surname: String, mail: String)
    }
}