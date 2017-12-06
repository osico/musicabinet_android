package com.musicabinet.mobile.model.instrument.matrix.local

import java.io.Serializable

/**
 * @author Kirchhoff-
 */
data class InstrumentCourse(val name: String, var productPrice: Float,
                            val id: String, val percent: Float?, var productActive: Boolean,
                            var productAvailable: Boolean?, val lessonGroups: List<InstrumentGroup>) : Serializable