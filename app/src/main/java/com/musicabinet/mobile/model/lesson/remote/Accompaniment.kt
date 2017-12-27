package com.musicabinet.mobile.model.lesson.remote

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class Accompaniment(@SerializedName("id") val id: String,
                         @SerializedName("nameLocalized") val name: String,
                         @SerializedName("trackOne") val drums: StoredFile?,
                         @SerializedName("trackTwo") val bass: StoredFile?,
                         @SerializedName("trackThree") val keys: StoredFile?)