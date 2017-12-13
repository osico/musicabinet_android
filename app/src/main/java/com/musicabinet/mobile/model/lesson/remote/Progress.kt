package com.musicabinet.mobile.model.lesson.remote

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class Progress(@SerializedName("timeSpent") val timeSpent: Long)