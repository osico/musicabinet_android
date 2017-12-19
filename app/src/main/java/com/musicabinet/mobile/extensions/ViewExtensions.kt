package com.musicabinet.mobile.extensions

import android.os.Build
import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
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

fun TextView.setColorText(@ColorRes color: Int) {
    this.setTextColor(ContextCompat.getColor(context, color))
}

fun TextView.setFormatText(string: String, formatArgs: Any) {
    text = string.format(formatArgs)
}

fun TextView.setTextFromResource(@StringRes stringId: Int, formatArgs: Any) =
        setFormatText(resources.getString(stringId), formatArgs)

fun EditText.getString() = text.toString()

fun TextView.displayHtmlText(text: String) {
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
        setText(Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY))
    } else {
        setText(Html.fromHtml(text))
    }
}