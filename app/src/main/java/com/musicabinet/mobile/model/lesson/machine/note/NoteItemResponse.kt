package com.musicabinet.mobile.model.lesson.machine.note

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class NoteItemResponse(@SerializedName("data") val noteList: MutableList<NoteItem>)