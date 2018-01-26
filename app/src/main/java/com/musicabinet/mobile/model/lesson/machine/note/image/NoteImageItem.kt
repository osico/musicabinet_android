package com.musicabinet.mobile.model.lesson.machine.note.image

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class NoteImageItem(@SerializedName("id") val id: String,
                         @SerializedName("elements") val list: List<NoteElement>)