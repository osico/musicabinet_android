package com.musicabinet.mobile.ui.cabinet.change

/**
 * @author Kirchhoff-
 */
class ChangePasswordPresenter(private val view: ChangePasswordContract.View)
    : ChangePasswordContract.Presenter {


    override fun onUserType(email: String) {
        view.enableSendButton(!email.isEmpty())
    }

    override fun changePassword(email: String) {
        if (email == "password@mail.ru")
            view.moveToChangePasswordSuccessScreen()
        else
            view.showMailError()
    }

}