package com.musicabinet.mobile.model.lesson.remote

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class StoredFile(@SerializedName("id") val id: String,
                      @SerializedName("meta") val meta: String,
                      @SerializedName("dataAvailable") val dataAvailable: Boolean)