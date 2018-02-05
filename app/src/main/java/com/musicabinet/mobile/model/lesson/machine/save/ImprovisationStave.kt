package com.musicabinet.mobile.model.lesson.machine.save

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class ImprovisationStave(@SerializedName("id") val id: String?,
                              @SerializedName("name") val name: String,
                              @SerializedName("description") val description: String?,
                              @SerializedName("staveTypeId") val staveTypeId: String,
                              @SerializedName("staveKindId") val staveKindId: String,
                              @SerializedName("storedFile") val storedFile: ImprovisationStoredFile)