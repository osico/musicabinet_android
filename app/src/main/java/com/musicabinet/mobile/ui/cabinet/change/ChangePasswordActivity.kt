package com.musicabinet.mobile.ui.cabinet.change

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.disableErrorOnType
import com.musicabinet.mobile.extensions.getString
import com.musicabinet.mobile.extensions.toast
import com.musicabinet.mobile.ui.ActivityWithBackButton
import com.musicabinet.mobile.ui.cabinet.SuccessChangePasswordActivity
import com.musicabinet.mobile.utils.TextWatcherAdapter
import kotlinx.android.synthetic.main.activity_change_password.*
/**
 * @author Kirchhoff-
 */
class ChangePasswordActivity : ActivityWithBackButton(), ChangePasswordContract.View {

    private lateinit var presenter: ChangePasswordContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = ChangePasswordPresenter(this)

        bSend.setOnClickListener { presenter.changePassword(edEmail.getString()) }

        edEmail.addTextChangedListener(userEmailInformationTextWatcher)
        edEmail.disableErrorOnType(ilEmail)
    }

    override fun inflateLayout(): View {
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        return inflater.inflate(R.layout.activity_change_password, null, false)
    }

    override fun showCompanyIcon() = true

    override fun enableSendButton(enable: Boolean) {
        bSend.isEnabled = enable
    }

    override fun showError() {
        toast(R.string.email_not_exist_error)
    }

    override fun showMailError() {
        ilEmail.isErrorEnabled = true
        ilEmail.error = getString(R.string.email_error)
    }

    override fun moveToChangePasswordSuccessScreen() {
        val intent = Intent(this, SuccessChangePasswordActivity::class.java)
        startActivity(intent)
    }

    private val userEmailInformationTextWatcher = object : TextWatcherAdapter() {

        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            presenter.onUserType(edEmail.getString())
        }
    }

}