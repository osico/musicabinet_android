package com.musicabinet.mobile.model.instrument.matrix

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class Course(@SerializedName("name") val name: String,
                  @SerializedName("productPriceAsFractional") var productPrice: Float,
                  @SerializedName("quads") val lessonGroups: List<LessonGroup>)