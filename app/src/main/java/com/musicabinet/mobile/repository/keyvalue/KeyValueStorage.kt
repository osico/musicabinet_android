package com.musicabinet.mobile.repository.keyvalue

import com.musicabinet.mobile.model.profile.UserProfile

/**
 * @author Kirchhoff-
 */
interface KeyValueStorage {

    companion object {
        const val USER_NAME_KEY = "USER_NAME_KEY"
        const val USER_EMAIL_KEY = "USER_EMAIL_KEY"
    }

    fun saveUserInformation(userProfile: UserProfile)

    fun getUserName(): String

    fun getUserEmail(): String

    fun isUserExist(): Boolean

    fun clear()
}