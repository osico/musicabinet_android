package com.musicabinet.mobile.model.instrument.matrix.local

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @author Kirchhoff-
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class InstrumentCourse(val name: String, var productPrice: Float,
                            val id: String, val percent: Float?, var productActive: Boolean,
                            var productAvailable: Boolean?, val lessonGroups: List<InstrumentGroup>) : Parcelable