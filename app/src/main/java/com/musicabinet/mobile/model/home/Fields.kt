package com.musicabinet.mobile.model.home

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class Field(@SerializedName("text") val text: String,
                 @SerializedName("url") var url: String?,
                 @SerializedName("imageUrl") var imageUrl: String?,
                 @SerializedName("style") var style: String?) {

    fun hasBorder(): Boolean {
        if (style != null && style == "bordered")
            return true

        return false
    }
}