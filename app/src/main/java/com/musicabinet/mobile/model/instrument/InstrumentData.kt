package com.musicabinet.mobile.model.instrument

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class InstrumentData(@SerializedName("data") val instrumentList: List<InstrumentDataElement>)