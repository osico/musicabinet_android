package com.musicabinet.mobile.ui.signup.user

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.getString
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
    }

    override fun showMailError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun enableNextButton(enable: Boolean) {
        bNext.isEnabled = enable
    }

    override fun moveToSetPassword() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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