package com.musicabinet.mobile.ui.signup.password

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.musicabinet.mobile.Injection
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.disableErrorOnType
import com.musicabinet.mobile.extensions.getString
import com.musicabinet.mobile.ui.ActivityWithBackButton
import com.musicabinet.mobile.ui.signup.SignUpFinishActivity
import com.musicabinet.mobile.ui.view.LoadingDialog
import com.musicabinet.mobile.utils.TextWatcherAdapter
import kotlinx.android.synthetic.main.activity_sign_up_password.*
import org.jetbrains.anko.toast

/**
 * @author Kirchhoff-
 */
class SignUpPasswordActivity : ActivityWithBackButton(), SignUpPasswordContract.View {

    companion object {
        const val SIGN_UP_EMAIL_ARG = "SIGN_UP_EMAIL_ARG"
        const val SIGN_UP_NAME_ARG = "SIGN_UP_MAIL_ARG"
        const val SIGN_UP_SURNAME_ARG = "SIGN_UP_SURNAME_ARG"
    }

    private lateinit var presenter: SignUpPasswordContract.Presenter

    private var loadingDialog: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = SignUpPasswordPresenter(this, Injection.provideRepository())

        bNext.isEnabled = false
        bNext.setOnClickListener {
            presenter.registerUser(intent.getStringExtra(SIGN_UP_EMAIL_ARG),
                    intent.getStringExtra(SIGN_UP_NAME_ARG),
                    intent.getStringExtra(SIGN_UP_SURNAME_ARG),
                    edPassword.getString(),
                    edConfirmPassword.getString())
        }

        edPassword.addTextChangedListener(userPasswordInformationTextWatcher)
        edConfirmPassword.addTextChangedListener(userPasswordInformationTextWatcher)
        edConfirmPassword.disableErrorOnType(ilConfirmPassword)
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribe()
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

    override fun showLoading(show: Boolean) {
        loadingDialog?.dismiss()

        if (show) {
            loadingDialog = LoadingDialog()
            loadingDialog?.show(supportFragmentManager, "TAG")
        }
    }

    override fun showError() {
        toast(R.string.internal_error)
    }

    private val userPasswordInformationTextWatcher = object : TextWatcherAdapter() {

        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            presenter.onUserType(edPassword.getString(), edConfirmPassword.getString())
        }
    }
}