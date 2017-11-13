package com.musicabinet.mobile.model.home

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class HomeData(@SerializedName("pages") val pages: Int,
                    @SerializedName("partCount") val partCount: Int,
                    @SerializedName("totalCount") val totalCount: Int,
                    @SerializedName("data") val fields: List<HomeDataElement>)