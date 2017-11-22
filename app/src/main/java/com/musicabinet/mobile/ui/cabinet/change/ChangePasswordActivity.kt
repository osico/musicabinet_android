package com.musicabinet.mobile.ui.cabinet.change

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.disableErrorOnType
import com.musicabinet.mobile.extensions.getString
import com.musicabinet.mobile.utils.TextWatcherAdapter
import kotlinx.android.synthetic.main.activity_change_password.*
import org.jetbrains.anko.toast

/**
 * @author Kirchhoff-
 */
class ChangePasswordActivity : AppCompatActivity(), ChangePasswordContract.View {

    private lateinit var presenter: ChangePasswordContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = ChangePasswordPresenter(this)

        bSend.setOnClickListener { presenter.changePassword(edEmail.getString()) }

        edEmail.addTextChangedListener(userEmailInformationTextWatcher)
        edEmail.disableErrorOnType(ilEmail)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home)
            onBackPressed()

        return super.onOptionsItemSelected(item)
    }

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
        //Here should be moving to SuccessChangePasswordScreen
    }


    private val userEmailInformationTextWatcher = object : TextWatcherAdapter() {

        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            presenter.onUserType(edEmail.getString())
        }
    }

}