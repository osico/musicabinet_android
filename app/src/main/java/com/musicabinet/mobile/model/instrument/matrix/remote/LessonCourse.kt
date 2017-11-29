package com.musicabinet.mobile.model.instrument.matrix.remote

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class LessonCourse(@SerializedName("name") val name: String,
                        @SerializedName("productPriceAsFractional") var productPrice: Float,
                        @SerializedName("percentCompleted") var percent: Float?,
                        @SerializedName("id") val id: String,
                        @SerializedName("quads") val lessonGroups: List<LessonGroup>)