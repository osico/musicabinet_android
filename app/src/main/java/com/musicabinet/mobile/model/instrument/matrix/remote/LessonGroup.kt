package com.musicabinet.mobile.model.instrument.matrix.remote

import com.google.gson.annotations.SerializedName
import com.musicabinet.mobile.model.instrument.matrix.LessonItem

/**
 * @author Kirchhoff-
 */
data class LessonGroup(@SerializedName("lessonGroups") val lessonList: List<LessonItem>)