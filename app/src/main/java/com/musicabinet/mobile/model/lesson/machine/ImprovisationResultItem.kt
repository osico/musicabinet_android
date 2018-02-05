package com.musicabinet.mobile.model.lesson.machine

import android.annotation.SuppressLint
import android.os.Parcelable
import com.musicabinet.mobile.model.lesson.machine.note.image.NoteElement
import kotlinx.android.parcel.Parcelize

/**
 * @author Kirchhoff-
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class ImprovisationResultItem(val toneOrChordResult: ToneOrChordResult?,
                                   val noteElement: NoteElement?,
                                   val fileDataItem: FileDataItem?) : Parcelable