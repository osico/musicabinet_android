package com.musicabinet.mobile.model.lesson.machine

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @author Kirchhoff-
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class ToneOrChord(val id: String, val name: String, val sortOrder: Int) : Parcelable,
        Comparable<ToneOrChord> {

    override fun compareTo(other: ToneOrChord) = sortOrder - other.sortOrder

}