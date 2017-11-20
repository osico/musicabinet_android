package com.musicabinet.mobile.ui.signup.password

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.musicabinet.mobile.R

/**
 * @author Kirchhoff-
 */
class SignUpPasswordActivity : AppCompatActivity(), SignUpPasswordContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_password)
    }

    override fun showPasswordMismatchError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun moveToFinishRegistration() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}