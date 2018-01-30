package com.musicabinet.mobile.model.lesson.remote

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * @author Kirchhoff-
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class StoredFile(@SerializedName("id") val id: String,
                      @SerializedName("meta") val meta: String,
                      @SerializedName("dataAvailable") val dataAvailable: Boolean) : Parcelable