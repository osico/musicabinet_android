package com.musicabinet.mobile.model.instrument.matrix

import com.google.gson.annotations.SerializedName
import com.musicabinet.mobile.model.instrument.matrix.remote.LessonCourse

/**
 * @author Kirchhoff-
 */
data class InstrumentMatrixResponse(@SerializedName("modules") val modules: List<Modules>,
                                    @SerializedName("courses") val courses: List<LessonCourse>)