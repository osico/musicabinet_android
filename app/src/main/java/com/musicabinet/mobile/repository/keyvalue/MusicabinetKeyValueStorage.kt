package com.musicabinet.mobile.repository.keyvalue

import android.preference.PreferenceManager
import com.musicabinet.mobile.MusicabinetApp
import com.musicabinet.mobile.model.profile.UserProfile

/**
 * @author Kirchhoff-
 */
object MusicabinetKeyValueStorage : KeyValueStorage {

    private val preferences = PreferenceManager.getDefaultSharedPreferences(MusicabinetApp.get())

    override fun saveUserInformation(userProfile: UserProfile) {
        val editor = preferences.edit()
        editor.putString(KeyValueStorage.USER_NAME_KEY, userProfile.formattedUserName)
        editor.putString(KeyValueStorage.USER_EMAIL_KEY, userProfile.email)
        editor.apply()
    }

    override fun getUserName() = preferences.getString(KeyValueStorage.USER_NAME_KEY, "")

    override fun getUserEmail() = preferences.getString(KeyValueStorage.USER_EMAIL_KEY, "")

    override fun isUserExist() = preferences.contains(KeyValueStorage.USER_NAME_KEY)

    override fun saveSelectedInstrumentId(id: String) {
        val editor = preferences.edit()
        editor.putString(KeyValueStorage.SELECT_INSTRUMENT_ID, id)
        editor.apply()
    }

    override fun getSelectedInstrumentId() = preferences.getString(KeyValueStorage.SELECT_INSTRUMENT_ID, "")

    override fun clear() {
        preferences.edit().clear().apply()
    }

}