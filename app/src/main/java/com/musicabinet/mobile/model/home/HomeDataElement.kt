package com.musicabinet.mobile.model.home

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class HomeDataElement(@SerializedName("name") val name: String,
                           @SerializedName("fields") var dataField: Field,
                           @SerializedName("elementDt") val date: String)