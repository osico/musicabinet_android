package com.musicabinet.mobile.ui.signup.user

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.disableErrorOnType
import com.musicabinet.mobile.extensions.getString
import com.musicabinet.mobile.ui.signup.password.SignUpPasswordActivity
import com.musicabinet.mobile.utils.TextWatcherAdapter
import kotlinx.android.synthetic.main.activity_sign_up_user.*

/**
 * @author Kirchhoff-
 */
class SignUpUserActivity : AppCompatActivity(), SignUpUserContract.View {

    private lateinit var presenter: SignUpUserContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_user)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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

    override fun showMailError() {
        ilEmail.isErrorEnabled = true
        ilEmail.error = getString(R.string.email_error)
    }

    override fun enableNextButton(enable: Boolean) {
        bNext.isEnabled = enable
    }

    override fun moveToSetPassword() {
        intent = Intent(this, SignUpPasswordActivity::class.java)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home)
            onBackPressed()

        return super.onOptionsItemSelected(item)
    }

    private val userInformationTextWatcher = object : TextWatcherAdapter() {

        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            presenter.onUserType(edUserName.getString(), edUserSurname.getString(),
                    edEmail.getString())
        }
    }
}