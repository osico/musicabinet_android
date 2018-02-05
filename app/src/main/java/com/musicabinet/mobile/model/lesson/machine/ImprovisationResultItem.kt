package com.musicabinet.mobile.model.lesson.machine

import com.musicabinet.mobile.model.lesson.machine.note.image.NoteElement

/**
 * @author Kirchhoff-
 */
data class ImprovisationResultItem(val toneOrChordResult: ToneOrChordResult?,
                                   val noteElement: NoteElement?,
                                   val fileDataItem: FileDataItem?)