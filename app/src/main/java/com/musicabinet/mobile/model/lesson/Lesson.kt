package com.musicabinet.mobile.model.lesson

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class Lesson(@SerializedName("id") val id: String,
                  @SerializedName("nameLocalized") val name: String)