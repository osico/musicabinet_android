package com.musicabinet.mobile.model.lesson

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * @author Kirchhoff-
 */
data class Lesson(@SerializedName("id") val id: String,
                  @SerializedName("nameLocalized") val name: String,
                  @SerializedName("sortOrder") val sortOrder: Int) : Comparable<Lesson>, Serializable {

    override fun compareTo(other: Lesson) = sortOrder - other.sortOrder

}