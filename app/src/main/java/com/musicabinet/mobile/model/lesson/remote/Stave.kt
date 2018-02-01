package com.musicabinet.mobile.model.lesson.remote

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class Stave(@SerializedName("id") val id: String,
                 @SerializedName("nameLocalized") val name: String,
                 @SerializedName("staveTypeId") val staveTypeId: String,
                 @SerializedName("storedFile") val file: StoredFile) {


    fun isGuideMachineStave(): Boolean {
        if (staveTypeId == "54480ce1-00eb-4179-a2b6-f74daa6b9e73")
            return true

        return false
    }
}