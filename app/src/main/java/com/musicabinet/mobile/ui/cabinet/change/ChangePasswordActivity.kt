package com.musicabinet.mobile.ui.cabinet.change

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.musicabinet.mobile.R

/**
 * @author Kirchhoff-
 */
class ChangePasswordActivity : AppCompatActivity(), ChangePasswordContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
    }

    override fun enableSendButton(enable: Boolean) {
    }

    override fun showError() {
    }

    override fun showMailError() {
    }

    override fun moveToChangePasswordSuccessScreen() {
    }

}