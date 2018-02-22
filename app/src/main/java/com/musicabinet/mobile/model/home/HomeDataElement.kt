package com.musicabinet.mobile.model.home

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class HomeDataElement(@SerializedName("nameLocalized") val name: String,
                           @SerializedName("fieldsLocalized") var dataField: Field,
                           @SerializedName("elementDt") val date: String)