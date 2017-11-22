package com.musicabinet.mobile.ui.cabinet.password

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.musicabinet.mobile.R

/**
 * @author Kirchhoff-
 */
class CabinetPasswordActivity : AppCompatActivity(), CabinetPasswordContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cabinet_password)
    }

    override fun enableNextButton(enable: Boolean) {
    }

    override fun moveToForgotPasswordScreen() {
    }

    override fun showPasswordError() {
    }

    override fun showEmailError() {
    }

    override fun moveToHomeScreen() {
    }

}