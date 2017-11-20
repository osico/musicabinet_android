package com.musicabinet.mobile.ui.signup.user

import com.musicabinet.mobile.extensions.isValidEmail

/**
 * @author Kirchhoff-
 */
class SignUpUserPresenter(private val view: SignUpUserContract.View) : SignUpUserContract.Presenter {

    override fun onUserType(name: String, surname: String, mail: String) {
        view.enableNextButton(!name.isEmpty() && !surname.isEmpty() && !mail.isEmpty())
    }

    override fun setUserData(name: String, surname: String, mail: String) {
        if (mail.isValidEmail())
            view.moveToSetPassword()
        else
            view.showMailError()
    }


}