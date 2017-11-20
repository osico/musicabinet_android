package com.musicabinet.mobile.ui.signup.password

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.disableErrorOnType
import com.musicabinet.mobile.extensions.getString
import com.musicabinet.mobile.utils.TextWatcherAdapter
import kotlinx.android.synthetic.main.activity_sign_up_password.*

/**
 * @author Kirchhoff-
 */
class SignUpPasswordActivity : AppCompatActivity(), SignUpPasswordContract.View {

    private lateinit var presenter: SignUpPasswordContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_password)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = SignUpPasswordPresenter(this)

        bNext.isEnabled = false
        bNext.setOnClickListener {
            presenter.registerUser(edPassword.getString(),
                    edConfirmPassword.getString())
        }

        edPassword.addTextChangedListener(userPasswordInformationTextWatcher)
        edConfirmPassword.addTextChangedListener(userPasswordInformationTextWatcher)
        edConfirmPassword.disableErrorOnType(ilConfirmPassword)
    }

    override fun showPasswordMismatchError() {
        ilConfirmPassword.isErrorEnabled = true
        ilConfirmPassword.error = getString(R.string.password_mismatch_error)
    }

    override fun moveToFinishRegistration() {
    }

    override fun enableNextButton(enable: Boolean) {
        bNext.isEnabled = enable
    }

    private val userPasswordInformationTextWatcher = object : TextWatcherAdapter() {

        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            presenter.onUserType(edPassword.getString(), edConfirmPassword.getString())
        }
    }
}