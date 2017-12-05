package com.musicabinet.mobile.model.order.finish

/**
 * @author Kirchhoff-
 */
data class OrderFinishExecuteBody(val orderId: String, val paymentSystemId: String,
                                  val params: OrderFinishExecuteParams)