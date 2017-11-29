package com.musicabinet.mobile.model.instrument.matrix.filter

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class InstrumentFilterItem(@SerializedName("id") val id: String,
                                @SerializedName("name") val name: String,
                                @SerializedName("sortOrder") val sortOrder: Int) : Comparable<InstrumentFilterItem> {

    override fun compareTo(other: InstrumentFilterItem) = sortOrder - other.sortOrder

}