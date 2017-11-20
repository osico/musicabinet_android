package com.musicabinet.mobile.ui.signup.password

/**
 * @author Kirchhoff-
 */
class SignUpPasswordPresenter(private val view: SignUpPasswordContract.View)
    : SignUpPasswordContract.Presenter {


    override fun onUserType(password: String, confirmPassword: String) {
        view.enableNextButton(!password.isEmpty() && !confirmPassword.isEmpty())
    }

    override fun registerUser(password: String, confirmPassword: String) {
        if (password != confirmPassword) {
            view.showPasswordMismatchError()
            return
        }

        view.moveToFinishRegistration()
    }

}