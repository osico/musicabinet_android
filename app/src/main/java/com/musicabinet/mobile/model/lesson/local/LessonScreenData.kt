package com.musicabinet.mobile.model.lesson.local

/**
 * @author Kirchhoff-
 */
data class LessonScreenData(val title: String, val methodList: List<MethodItem>,
                            val lessonImages: List<LessonData>, val spendTime: Long)