package com.musicabinet.mobile.model.lesson.machine.save

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class ImprovisationStoredFile(@SerializedName("id") val id: String?,
                                   @SerializedName("storeModeId") val storeModeId: String)