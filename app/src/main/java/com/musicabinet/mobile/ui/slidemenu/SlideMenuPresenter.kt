package com.musicabinet.mobile.ui.slidemenu

import com.musicabinet.mobile.api.ApiFactory
import com.musicabinet.mobile.repository.keyvalue.KeyValueStorage

/**
 * @author Kirchhoff-
 */
class SlideMenuPresenter(private val view: SlideMenuContract.View,
                         private val storage: KeyValueStorage) : SlideMenuContract.Presenter {

    override fun onEducationClick() {
        view.moveToEducation()
    }

    override fun onHomeClick() {
        view.moveToHome()
    }

    override fun onLogOutClick() {
        storage.clear()
        ApiFactory.clearCookie()
        view.logout()
    }

    override fun onMyAccountClick() {
        view.moveToMyAccount()
    }

    override fun configMenuVisibility() {
        if (storage.isUserExist())
            view.showLoginUserMenu(storage.getUserName(), storage.getUserEmail())
        else
            view.showNotLoginUserMenu()
    }

}