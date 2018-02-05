package com.musicabinet.mobile.model.lesson.machine

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @author Kirchhoff-
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class ImprovisationResult(val row: String, val list: List<ImprovisationResultItem>) : Parcelable