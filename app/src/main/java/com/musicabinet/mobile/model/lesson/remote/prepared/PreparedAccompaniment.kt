package com.musicabinet.mobile.model.lesson.remote.prepared

import com.google.gson.annotations.SerializedName
import com.musicabinet.mobile.model.lesson.remote.Accompaniment

/**
 * @author Kirchhoff-
 */
data class PreparedAccompaniment(@SerializedName("data") val preparedAccompaniment: List<Accompaniment>)