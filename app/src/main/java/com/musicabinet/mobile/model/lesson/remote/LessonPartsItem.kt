package com.musicabinet.mobile.model.lesson.remote

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class LessonPartsItem(@SerializedName("id") val id: String,
                           @SerializedName("nameLocalized") val name: String,
                           @SerializedName("descriptionLocalized") val description: String,
                           @SerializedName("video") val video: Video,
                           @SerializedName("exercises") val exercisesList: List<ExerciseItem>)