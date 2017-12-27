package com.musicabinet.mobile.model.lesson.remote

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class LessonPartsItem(@SerializedName("id") val id: String,
                           @SerializedName("nameLocalized") val name: String,
                           @SerializedName("descriptionLocalized") val description: String,
                           @SerializedName("video") var video: Video?,
                           @SerializedName("lessonPartTypeId") private val lessonPartsTypeId: String?,
                           @SerializedName("exercises") val exercisesList: List<ExerciseItem>) {

    fun isLessonPartsExist(): Boolean {
        if (lessonPartsTypeId == null)
            return true

        return "54480ce1-00eb-4179-a2b6-f74daa6b9e71" == lessonPartsTypeId
    }
}