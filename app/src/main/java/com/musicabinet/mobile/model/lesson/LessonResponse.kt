package com.musicabinet.mobile.model.lesson

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class LessonResponse(@SerializedName("id") val id: String,
                          @SerializedName("nameLocalized") val name: String,
                          @SerializedName("progress") val progress: Progress,
                          @SerializedName("lessonParts") val lessonParts: List<LessonPartsItem>)