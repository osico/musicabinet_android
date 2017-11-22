package com.musicabinet.mobile.ui.cabinet.password

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.disableErrorOnType
import com.musicabinet.mobile.extensions.getString
import com.musicabinet.mobile.utils.TextWatcherAdapter
import kotlinx.android.synthetic.main.activity_cabinet_password.*

/**
 * @author Kirchhoff-
 */
class CabinetPasswordActivity : AppCompatActivity(), CabinetPasswordContract.View {

    private lateinit var presenter: CabinetPasswordContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cabinet_password)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = CabinetPasswordPresenter(this)

        bNext.isEnabled = false
        bNext.setOnClickListener {
            presenter.loginUser("", edPassword.getString())
        }

        edPassword.addTextChangedListener(userPasswordInformationTextWatcher)
        edPassword.disableErrorOnType(ilPassword)
    }

    override fun enableNextButton(enable: Boolean) {
        bNext.isEnabled = enable
    }

    override fun moveToForgotPasswordScreen() {
        // Here should be moving to ForgotPasswordScreen
    }

    override fun showPasswordError() {
        ilPassword.isErrorEnabled = true
        ilPassword.error = getString(R.string.wrong_password_error)
    }

    override fun showEmailError() {
        // Here should be moving to previous activity with negative result
    }

    override fun moveToHomeScreen() {
        // Here should be moving to previous activity with positive result
    }

    private val userPasswordInformationTextWatcher = object : TextWatcherAdapter() {

        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            presenter.onUserType(edPassword.getString())
        }
    }

}