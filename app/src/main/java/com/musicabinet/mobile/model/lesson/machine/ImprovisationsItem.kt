package com.musicabinet.mobile.model.lesson.machine

import com.google.gson.annotations.SerializedName
import com.musicabinet.mobile.model.lesson.remote.Stave

/**
 * @author Kirchhoff-
 */
data class ImprovisationsItem(@SerializedName("id") val id: String,
                              @SerializedName("stave") val stave: Stave)