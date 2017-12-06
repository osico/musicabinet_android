package com.musicabinet.mobile.model.order.execute

/**
 * @author Kirchhoff-
 */
data class OrderExecuteBody(val orderId: String, val paymentSystemId: String,
                            val params: OrderExecuteParams)