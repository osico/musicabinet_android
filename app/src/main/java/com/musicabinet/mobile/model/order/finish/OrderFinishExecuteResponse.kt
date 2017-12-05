package com.musicabinet.mobile.model.order.finish

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class OrderFinishExecuteResponse(@SerializedName("processed") val processed: Boolean)