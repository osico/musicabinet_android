package com.musicabinet.mobile.model.instrument.matrix.filter

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class InstrumentFilterResponse(@SerializedName("data") val filterList: List<InstrumentFilterItem>)