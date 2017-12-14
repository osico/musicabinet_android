package com.musicabinet.mobile.model.lesson.remote

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class Video(@SerializedName("id") val id: String,
                 @SerializedName("nameLocalized") val name: String,
                 @SerializedName("storedFile") var video: StoredFile?,
                 @SerializedName("description") var description: String?)