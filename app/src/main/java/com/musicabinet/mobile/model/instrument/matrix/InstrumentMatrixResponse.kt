package com.musicabinet.mobile.model.instrument.matrix

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class InstrumentMatrixResponse(@SerializedName("modules") val modules: List<Modules>,
                                    @SerializedName("courses") val courses: List<Course>)