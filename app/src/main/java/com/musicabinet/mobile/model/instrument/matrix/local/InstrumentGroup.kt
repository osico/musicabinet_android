package com.musicabinet.mobile.model.instrument.matrix.local

import com.musicabinet.mobile.model.instrument.matrix.LessonItem
import com.musicabinet.mobile.model.instrument.matrix.Modules

/**
 * @author Kirchhoff-
 */
data class InstrumentGroup(val lessonList: List<LessonItem>, val module: Modules)