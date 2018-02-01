package com.musicabinet.mobile.model.lesson.remote

import com.google.gson.annotations.SerializedName
import com.musicabinet.mobile.model.lesson.machine.ImprovisationsItem

/**
 * @author Kirchhoff-
 */
data class LessonResponse(@SerializedName("id") val id: String,
                          @SerializedName("nameLocalized") val name: String,
                          @SerializedName("progress") private val progress: Progress?,
                          @SerializedName("lessonTypeId") private val lessonTypeId: String?,
                          @SerializedName("lessonParts") val lessonParts: List<LessonPartsItem>,
                          @SerializedName("improvisations") val improvisations: List<ImprovisationsItem>,
                          @SerializedName("duration") val duration: Long) {


    fun getProgress(): Long {
        if (progress != null)
            return progress.timeSpent

        return 0
    }


    fun hasGuideMachine(): Boolean {
        if (lessonTypeId == "54480ce1-00eb-4179-a2b6-f74daa6b9e72")
            return true

        return false
    }
}