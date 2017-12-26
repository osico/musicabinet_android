package com.musicabinet.mobile.model.lesson.local

import com.musicabinet.mobile.model.lesson.remote.Accompaniment

/**
 * @author Kirchhoff-
 */
data class LessonScreenData(val id: String, val title: String, val methodList: List<MethodItem>,
                            val lessonImages: List<LessonData>, val accompaniments: Set<Accompaniment>,
                            val spendTime: Long)