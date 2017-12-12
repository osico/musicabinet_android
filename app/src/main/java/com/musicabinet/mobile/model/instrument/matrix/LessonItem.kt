package com.musicabinet.mobile.model.instrument.matrix

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * @author Kirchhoff-
 */
data class LessonItem(@SerializedName("id") val id: String,
                      @SerializedName("nameLocalized") val nameLocalized: String,
                      @SerializedName("progress") val progress: Float,
                      @SerializedName("programs") val programs: List<String>) : Serializable