package com.musicabinet.mobile.model.lesson.lesson

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * @author Kirchhoff-
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class Lesson(@SerializedName("id") val id: String,
                  @SerializedName("nameLocalized") val name: String,
                  @SerializedName("sortOrder") val sortOrder: Int) : Parcelable, Comparable<Lesson> {

    override fun compareTo(other: Lesson) = sortOrder - other.sortOrder

}