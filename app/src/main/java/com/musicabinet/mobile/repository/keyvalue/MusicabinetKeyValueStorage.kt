package com.musicabinet.mobile.repository.keyvalue

import android.preference.PreferenceManager
import com.musicabinet.mobile.MusicabinetApp

/**
 * @author Kirchhoff-
 */
object MusicabinetKeyValueStorage : KeyValueStorage {

    private val preferences = PreferenceManager.getDefaultSharedPreferences(MusicabinetApp.get())

    override fun saveUserInformation(userName: String, email: String) {
        val editor = preferences.edit()
        editor.putString(KeyValueStorage.USER_NAME_KEY, userName)
        editor.putString(KeyValueStorage.USER_EMAIL_KEY, email)
        editor.apply()
    }

    override fun getUserName() = preferences.getString(KeyValueStorage.USER_NAME_KEY, "")

    override fun getUserEmail() = preferences.getString(KeyValueStorage.USER_EMAIL_KEY, "")

    override fun isUserExist() = preferences.contains(KeyValueStorage.USER_NAME_KEY)

}