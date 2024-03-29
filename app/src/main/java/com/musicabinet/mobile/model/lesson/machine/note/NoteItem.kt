package com.musicabinet.mobile.model.lesson.machine.note

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class NoteItem(@SerializedName("id") val id: String,
                    @SerializedName("sortOrder") val sortOrder: Int,
                    @SerializedName("nameLocalized") val name: String,
                    @SerializedName("activeInLibrary") val activeInLibrary: Boolean?) : Comparable<NoteItem> {

    override fun compareTo(other: NoteItem) = sortOrder - other.sortOrder

}