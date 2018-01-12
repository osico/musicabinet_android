package com.musicabinet.mobile.model.instrument.matrix

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * @author Kirchhoff-
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class Modules(@SerializedName("id") val id: String,
                   @SerializedName("name") val name: String) : Parcelable