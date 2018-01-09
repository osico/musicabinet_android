package com.musicabinet.mobile.ui.cabinet.email

import com.musicabinet.mobile.extensions.isValidEmail

/**
 * @author Kirchhoff-
 */
class EnterEmailPresenter(private val view: EnterEmailContract.View) : EnterEmailContract.Presenter {

    override fun onUserTypeEmail(email: String) {
        view.enableNextButton(!email.isEmpty())
    }

    override fun onNextClick(mail: String) {
        if (mail.isValidEmail())
            view.moveToEnterPassword()
        else
            view.showMailError()
    }

}