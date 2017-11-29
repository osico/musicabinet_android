package com.musicabinet.mobile.model.instrument.matrix

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * @author Kirchhoff-
 */
data class Modules(@SerializedName("id") val id: String,
                   @SerializedName("name") val name: String) : Serializable