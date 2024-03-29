package com.musicabinet.mobile.ui.slidemenu

/**
 * @author Kirchhoff-
 */
interface SlideMenuContract {

    interface View {

        fun moveToEducation()

        fun moveToHome()

        fun logout()

        fun moveToMyAccount()

        fun showLoginUserMenu(userName: String, email: String)

        fun showNotLoginUserMenu()
    }

    interface Presenter {

        fun onEducationClick()

        fun onHomeClick()

        fun onLogOutClick()

        fun onMyAccountClick()

        fun configMenuVisibility()
    }

}