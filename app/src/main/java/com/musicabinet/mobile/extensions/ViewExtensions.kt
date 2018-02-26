package com.musicabinet.mobile.extensions

import android.content.Context
import android.graphics.PorterDuff
import android.os.Build
import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.squareup.picasso.Picasso

/**
 * @author Kirchhoff-
 */
fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun View.setVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}


fun ImageView.loadImage(url: String?) {
    if (!url.isNullOrEmpty())
        Picasso.with(context).load(url).into(this)
}

fun TextView.setFormatText(string: String, formatArgs: Any) {
    text = string.format(formatArgs)
}

fun TextView.setTextFromResource(@StringRes stringId: Int, formatArgs: Any) =
        setFormatText(resources.getString(stringId), formatArgs)

fun EditText.getString() = text.toString()

fun TextView.displayHtmlText(text: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        setText(Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY))
    } else {
        setText(Html.fromHtml(text))
    }
}

fun TextView.displayTimeValue(value: Long) {
    text = when (value) {
        0L -> "00"
        in 1..9 -> "0" + value
        else -> value.toString()
    }
}

fun ImageView.setTint(@ColorRes colorRes: Int) {
    setColorFilter(ContextCompat.getColor(context, colorRes),
            android.graphics.PorterDuff.Mode.MULTIPLY)
}

fun Button.textColor(@ColorRes color: Int) {
    setTextColor(ContextCompat.getColor(context, color))
}

fun Button.setBackgroundTint(@ColorRes color: Int) {
    background.setColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.MULTIPLY)
}

fun CheckedTextView.configVisibility() {
    if (isChecked) {
        isEnabled = true
        alpha = 1.0f
    } else {
        isEnabled = false
        alpha = 0.5f
    }
}

fun View.closeKeyboard() {
    val imm: InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE)
            as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
    imm.hideSoftInputFromWindow(applicationWindowToken, 0)
}