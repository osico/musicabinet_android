package com.musicabinet.mobile.model.instrument.matrix

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class LessonItem(@SerializedName("nameLocalized") val nameLocalized: String,
                      @SerializedName("progress") val progress: Float,
                      @SerializedName("programs") val programs: List<String>)