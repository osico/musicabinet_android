package com.musicabinet.mobile.extensions

import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
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

fun EditText.getString() = text.toString()