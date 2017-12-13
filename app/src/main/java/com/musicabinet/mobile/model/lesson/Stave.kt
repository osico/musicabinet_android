package com.musicabinet.mobile.model.lesson

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class Stave(@SerializedName("id") val id: String,
                 @SerializedName("nameLocalized") val name: String,
                 @SerializedName("storedFile") val file: StoredFile)