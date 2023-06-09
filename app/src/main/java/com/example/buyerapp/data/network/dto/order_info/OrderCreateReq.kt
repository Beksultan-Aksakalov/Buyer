package com.example.buyerapp.data.network.dto.order_info

import com.example.buyerapp.domain.model.Cart

data class OrderCreateReq(
    val items: List<OrderItemReq>,
    val paymentTypeId: Int,
    val storeId: Long,
    val totalPrice: Int
)

data class OrderItemReq(
    val itemId: Int,
    val price: Int,
    val qunatity: Int
)

fun Cart.toOrderDto() = OrderCreateReq(
    items = items.map { OrderItemReq(it.item.id, it.item.price * 100 * it.amount, it.amount) },
    paymentTypeId = 0,
    storeId = store!!.id,
    totalPrice = totalPrice * 100
)