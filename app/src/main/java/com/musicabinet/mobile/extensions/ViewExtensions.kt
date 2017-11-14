package com.musicabinet.mobile.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @author Kirchhoff-
 */
fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}