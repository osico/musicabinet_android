package com.musicabinet.mobile.ui.cabinet.password

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.disableErrorOnType
import com.musicabinet.mobile.extensions.getString
import com.musicabinet.mobile.utils.TextWatcherAdapter
import kotlinx.android.synthetic.main.activity_cabinet_password.*

/**
 * @author Kirchhoff-
 */
class CabinetPasswordActivity : AppCompatActivity(), CabinetPasswordContract.View {

    companion object {
        const val PASSWORD_REQUEST_CODE = 1001
        const val EMAIL_ARG = "EMAIL_ARG"
        const val RESULT_LOGIN = 10001
        const val RESULT_ERROR = 10002
    }

    private lateinit var presenter: CabinetPasswordContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cabinet_password)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = CabinetPasswordPresenter(this)

        bNext.isEnabled = false
        bNext.setOnClickListener {
            presenter.loginUser(intent.getStringExtra(EMAIL_ARG), edPassword.getString())
        }

        edPassword.addTextChangedListener(userPasswordInformationTextWatcher)
        edPassword.disableErrorOnType(ilPassword)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home)
            onBackPressed()

        return super.onOptionsItemSelected(item)
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