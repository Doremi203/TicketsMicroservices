package org.amogus.ticketsservice.api.api

import org.amogus.ticketsservice.api.requests.CreateOrderRequest
import org.amogus.ticketsservice.api.responses.OrderInfoResponse
import org.springframework.http.ResponseEntity

interface OrderApi {
    suspend fun createOrder(request: CreateOrderRequest): ResponseEntity<Int>
    suspend fun getOrderInfo(orderId: Int): ResponseEntity<OrderInfoResponse>
}