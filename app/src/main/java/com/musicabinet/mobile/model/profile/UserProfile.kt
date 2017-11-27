package com.musicabinet.mobile.model.profile

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class UserProfile(@SerializedName("email") val email: String,
                       @SerializedName("formattedUserName") val formattedUserName: String)