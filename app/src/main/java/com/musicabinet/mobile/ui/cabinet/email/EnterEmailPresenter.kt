package com.musicabinet.mobile.ui.cabinet.email

/**
 * @author Kirchhoff-
 */
class EnterEmailPresenter(private val view: EnterEmailContract.View) : EnterEmailContract.Presenter {

    override fun onUserTypeEmail(email: String) {
        view.enableNextButton(!email.isEmpty())
    }

    override fun onNextClick() {
        view.moveToEnterPassword()
    }

}