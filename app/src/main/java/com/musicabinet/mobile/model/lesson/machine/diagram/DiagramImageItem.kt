package com.musicabinet.mobile.model.lesson.machine.diagram

import com.google.gson.annotations.SerializedName
import com.musicabinet.mobile.model.lesson.remote.StoredFile

/**
 * @author Kirchhoff-
 */
data class DiagramImageItem(@SerializedName("id") val id: String,
                            @SerializedName("diagramToneName") val diagramToneName: String,
                            @SerializedName("diagramChordTypeName") val diagramChordTypeName: String,
                            @SerializedName("image") val image: StoredFile)