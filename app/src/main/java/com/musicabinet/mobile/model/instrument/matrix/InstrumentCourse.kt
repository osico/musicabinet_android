package com.musicabinet.mobile.model.instrument.matrix

/**
 * @author Kirchhoff-
 */
data class InstrumentCourse(val name: String, var productPrice: Float,
                            val lessonGroups: List<ModuleWithLessonGroup>)