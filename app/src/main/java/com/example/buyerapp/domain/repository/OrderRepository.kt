package com.example.buyerapp.domain.repository

import com.example.buyerapp.domain.model.order_info.Order

interface OrderRepository {
    suspend fun getOrders(startDate: String, endDate: String, pageNum: Int, pageSize: Int): List<Order>
}