package com.musicabinet.mobile.model.instrument.matrix

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class LessonGroup(@SerializedName("lessonGroups") val lessonList: List<LessonItem>)