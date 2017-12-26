package com.musicabinet.mobile.model.lesson.remote

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class ExerciseItem(@SerializedName("id") val id: String,
                        @SerializedName("nameLocalized") val name: String,
                        @SerializedName("accompaniment") val accompaniment: Accompaniment,
                        @SerializedName("stave") val stave: Stave?)