package com.musicabinet.mobile.model.lesson.machine.save

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class ImprovisationStaveResult(@SerializedName("id") val id: String?,
                                    @SerializedName("name") val name: String,
                                    @SerializedName("lessonId") val lessonId: String,
                                    @SerializedName("exerciseId") val exerciseId: String,
                                    @SerializedName("stave") val stave: ImprovisationStaveResult)