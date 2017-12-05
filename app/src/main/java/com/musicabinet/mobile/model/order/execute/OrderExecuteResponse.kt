package com.musicabinet.mobile.model.order.execute

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class OrderExecuteResponse(@SerializedName("params") val tokenItem: TokenItem)