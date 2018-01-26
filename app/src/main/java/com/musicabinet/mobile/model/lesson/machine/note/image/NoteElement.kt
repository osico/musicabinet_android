package com.musicabinet.mobile.model.lesson.machine.note.image

import com.google.gson.annotations.SerializedName
import com.musicabinet.mobile.model.lesson.remote.StoredFile

/**
 * @author Kirchhoff-
 */
data class NoteElement(@SerializedName("id") val id: String,
                       @SerializedName("image") val image: StoredFile,
                       @SerializedName("sortOrder") val sortOrder: Int)