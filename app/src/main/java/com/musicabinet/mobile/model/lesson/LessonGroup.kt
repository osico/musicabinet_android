package com.musicabinet.mobile.model.lesson

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class LessonGroup(@SerializedName("data") val lessonList: List<Lesson>)