package com.musicabinet.mobile.ui.slidemenu

/**
 * @author Kirchhoff-
 */
class SlideMenuPresenter(private val view: SlideMenuContract.View) : SlideMenuContract.Presenter {

    override fun onEducationClick() {
        view.moveToEducation()
    }

    override fun onHomeClick() {
        view.moveToHome()
    }

    override fun onLogOutClick() {
        view.logout()
    }

    override fun onMyAccountClick() {
        view.moveToMyAccount()
    }

}