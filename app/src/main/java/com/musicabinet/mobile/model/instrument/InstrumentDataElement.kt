package com.musicabinet.mobile.model.instrument

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class InstrumentDataElement(@SerializedName("id") val id: String,
                                 @SerializedName("active") val active: Boolean,
                                 @SerializedName("nameLocalized") val nameLocalized: String,
                                 @SerializedName("logo") val logo: String,
                                 @SerializedName("sortOrder") val sortOrder: Int) : Comparable<InstrumentDataElement> {

    override fun compareTo(other: InstrumentDataElement) = sortOrder - other.sortOrder

}