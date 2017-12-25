package com.musicabinet.mobile.model.lesson.local

/**
 * @author Kirchhoff-
 */
data class LessonScreenData(val id: String, val title: String, val methodList: List<MethodItem>,
                            val lessonImages: List<LessonData>, val spendTime: Long)