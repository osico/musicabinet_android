package com.musicabinet.mobile.ui.signup.user

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.musicabinet.mobile.R

/**
 * @author Kirchhoff-
 */
class SignUpUserActivity : AppCompatActivity(), SignUpUserContract.View {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_user)
    }

    override fun showEmptyNameError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showEmptySurnameError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showEmptyMailError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMailError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun enableNextButton(enable: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun moveToSetPassword() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}