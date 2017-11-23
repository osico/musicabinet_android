package com.musicabinet.mobile.ui.signup.password

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.disableErrorOnType
import com.musicabinet.mobile.extensions.getString
import com.musicabinet.mobile.ui.ActivityWithBackButton
import com.musicabinet.mobile.ui.signup.SignUpFinishActivity
import com.musicabinet.mobile.utils.TextWatcherAdapter
import kotlinx.android.synthetic.main.activity_sign_up_password.*

/**
 * @author Kirchhoff-
 */
class SignUpPasswordActivity : ActivityWithBackButton(), SignUpPasswordContract.View {

    private lateinit var presenter: SignUpPasswordContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

    override fun inflateLayout(): View {
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        return inflater.inflate(R.layout.activity_sign_up_password, null, false)
    }

    override fun showCompanyIcon() = false

    override fun showPasswordMismatchError() {
        ilConfirmPassword.isErrorEnabled = true
        ilConfirmPassword.error = getString(R.string.password_mismatch_error)
    }

    override fun moveToFinishRegistration() {
        intent = Intent(this, SignUpFinishActivity::class.java)
        startActivity(intent)
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