package com.musicabinet.mobile.ui.cabinet.email

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.musicabinet.mobile.R
import com.musicabinet.mobile.utils.TextWatcherAdapter
import kotlinx.android.synthetic.main.view_enter_email.view.*

/**
 * @author Kirchhoff-
 */
class EnterEmailView : LinearLayout, EnterEmailContract.View {

    private lateinit var presenter: EnterEmailContract.Presenter

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    fun init() {
        LayoutInflater.from(context).inflate(R.layout.view_enter_email, this, true)

        presenter = EnterEmailPresenter(this)
        bNext.isEnabled = false

        edEmail.addTextChangedListener(emailTextWatcher)
    }

    override fun enableNextButton(enable: Boolean) {
        bNext.isEnabled = enable
    }

    private val emailTextWatcher = object : TextWatcherAdapter() {

        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            presenter.onUserTypeEmail(edEmail.text.toString())
        }
    }
}