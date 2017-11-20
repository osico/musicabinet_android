package com.musicabinet.mobile.extensions

import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextWatcher
import java.util.regex.Pattern

/**
 * @author Kirchhoff-
 */
fun String.isValidEmail() = !this.isEmpty() &&
        Pattern.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", this)


fun TextInputEditText.disableErrorOnType(inputLayout: TextInputLayout) {

    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            inputLayout.isErrorEnabled = false
        }

        override fun afterTextChanged(s: Editable?) {

        }
    })
}