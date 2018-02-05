package com.musicabinet.mobile.model.lesson.machine.note.image

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.musicabinet.mobile.model.lesson.remote.StoredFile
import kotlinx.android.parcel.Parcelize

/**
 * @author Kirchhoff-
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class NoteElement(@SerializedName("id") val id: String,
                       @SerializedName("image") val image: StoredFile,
                       @SerializedName("sortOrder") val sortOrder: Int,
                       @SerializedName("code") val code: String) : Parcelable