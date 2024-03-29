package com.musicabinet.mobile.ui.cabinet.password

import android.annotation.SuppressLint
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
import com.musicabinet.mobile.ui.cabinet.change.ChangePasswordActivity
import com.musicabinet.mobile.ui.view.LoadingDialog
import com.musicabinet.mobile.utils.TextWatcherAdapter
import kotlinx.android.synthetic.main.activity_cabinet_password.*

/**
 * @author Kirchhoff-
 */
class CabinetPasswordActivity : ActivityWithBackButton(), CabinetPasswordContract.View {

    companion object {
        const val PASSWORD_REQUEST_CODE = 1001
        const val EMAIL_ARG = "EMAIL_ARG"
        const val RESULT_LOGIN = 10001
        const val RESULT_ERROR = 10002
    }

    private lateinit var presenter: CabinetPasswordContract.Presenter

    private var loadingDialog: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = CabinetPasswordPresenter(Injection.provideRepository(),
                Injection.provideStorage(), this)

        bNext.isEnabled = false
        bNext.setOnClickListener {
            presenter.loginUser(intent.getStringExtra(EMAIL_ARG), edPassword.getString())
        }

        edPassword.addTextChangedListener(userPasswordInformationTextWatcher)
        edPassword.disableErrorOnType(ilPassword)

        tvMissPassword.setOnClickListener {
            presenter.forgotPassword()
        }
    }

    @SuppressLint("InflateParams")
    override fun inflateLayout(): View {
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        return inflater.inflate(R.layout.activity_cabinet_password, null, false)
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribe()
    }

    override fun showCompanyIcon() = true

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


    override fun moveToForgotPasswordScreen() {
        val intent = Intent(this, ChangePasswordActivity::class.java)
        startActivity(intent)
    }

    override fun showPasswordError() {
        ilPassword.isErrorEnabled = true
        ilPassword.error = getString(R.string.wrong_password_error)
    }

    override fun showEmailError() {
        setResult(RESULT_ERROR)
        finish()
    }

    override fun moveToHomeScreen() {
        setResult(RESULT_LOGIN)
        finish()
    }

    private val userPasswordInformationTextWatcher = object : TextWatcherAdapter() {

        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            presenter.onUserType(edPassword.getString())
        }
    }

}