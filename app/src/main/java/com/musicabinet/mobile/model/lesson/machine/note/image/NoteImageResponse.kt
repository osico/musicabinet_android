package com.musicabinet.mobile.model.lesson.machine.note.image

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class NoteImageResponse(@SerializedName("data") val list: List<NoteImageItem>)