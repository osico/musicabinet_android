package com.musicabinet.mobile.model.lesson.remote

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class Exercises(@SerializedName("exercises") val exercisesList: List<ExerciseItem>)