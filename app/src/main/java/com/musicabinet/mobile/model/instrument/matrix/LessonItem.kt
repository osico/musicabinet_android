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
data class LessonItem(@SerializedName("id") val id: String,
                      @SerializedName("nameLocalized") val nameLocalized: String,
                      @SerializedName("progress") val progress: Float,
                      @SerializedName("productAvailable") val productAvailable: Boolean,
                      @SerializedName("programs") val programs: List<String>) : Parcelable