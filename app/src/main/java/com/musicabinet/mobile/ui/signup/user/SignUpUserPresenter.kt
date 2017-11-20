package com.musicabinet.mobile.ui.signup.user

/**
 * @author Kirchhoff-
 */
class SignUpUserPresenter(private val view: SignUpUserContract.View) : SignUpUserContract.Presenter {


    override fun onUserType(name: String, surname: String, mail: String) {
        view.enableNextButton(!name.isEmpty() && !surname.isEmpty() && !mail.isEmpty())
    }

}