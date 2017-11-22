package com.musicabinet.mobile.ui.cabinet.password

/**
 * @author Kirchhoff-
 */
class CabinetPasswordPresenter(private val view: CabinetPasswordContract.View)
    : CabinetPasswordContract.Presenter {

    override fun onUserType(password: String) {
        view.enableNextButton(!password.isEmpty())
    }

    override fun forgotPassword() {
        view.moveToForgotPasswordScreen()
    }

    override fun loginUser(email: String, password: String) {
        view.moveToHomeScreen()
    }

}