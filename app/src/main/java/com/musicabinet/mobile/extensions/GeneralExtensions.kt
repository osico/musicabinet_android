package com.musicabinet.mobile.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.musicabinet.mobile.R
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


fun Context.openVideoIntent(url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    intent.setDataAndType(Uri.parse(url), "video/mp4")

    val infos = packageManager.queryIntentActivities(intent, 0)

    if (infos.size > 0) {
        startActivity(intent)
    } else {
        Toast.makeText(this, R.string.error_open_video, Toast.LENGTH_LONG).show()
    }
}