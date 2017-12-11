package com.musicabinet.mobile.ui.signup.user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.disableErrorOnType
import com.musicabinet.mobile.extensions.getString
import com.musicabinet.mobile.ui.ActivityWithBackButton
import com.musicabinet.mobile.ui.signup.password.SignUpPasswordActivity
import com.musicabinet.mobile.utils.TextWatcherAdapter
import kotlinx.android.synthetic.main.activity_sign_up_user.*

/**
 * @author Kirchhoff-
 */
class SignUpUserActivity : ActivityWithBackButton(), SignUpUserContract.View {

    private lateinit var presenter: SignUpUserContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = SignUpUserPresenter(this)

        edUserName.addTextChangedListener(userInformationTextWatcher)
        edUserSurname.addTextChangedListener(userInformationTextWatcher)
        edEmail.addTextChangedListener(userInformationTextWatcher)

        bNext.isEnabled = false
        edEmail.disableErrorOnType(ilEmail)

        bNext.setOnClickListener {
            presenter.setUserData(edUserName.getString(), edUserSurname.getString(),
                    edEmail.getString())
        }
    }

    override fun inflateLayout(): View {
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        return inflater.inflate(R.layout.activity_sign_up_user, null, false)
    }


    override fun showCompanyIcon() = false

    override fun showMailError() {
        ilEmail.isErrorEnabled = true
        ilEmail.error = getString(R.string.email_error)
    }

    override fun enableNextButton(enable: Boolean) {
        bNext.isEnabled = enable
    }

    override fun moveToSetPassword() {
        intent = Intent(this, SignUpPasswordActivity::class.java)
        intent.putExtra(SignUpPasswordActivity.SIGN_UP_EMAIL_ARG, edEmail.getString())
        intent.putExtra(SignUpPasswordActivity.SIGN_UP_NAME_ARG, edUserName.getString())
        intent.putExtra(SignUpPasswordActivity.SIGN_UP_SURNAME_ARG, edUserSurname.getString())
        startActivity(intent)
    }

    private val userInformationTextWatcher = object : TextWatcherAdapter() {

        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            presenter.onUserType(edUserName.getString(), edUserSurname.getString(),
                    edEmail.getString())
        }
    }
}