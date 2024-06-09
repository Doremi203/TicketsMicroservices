package org.amogus.ticketsservice.api.controllers

import jakarta.validation.Valid
import org.amogus.ticketsservice.api.api.OrderApi
import org.amogus.ticketsservice.api.requests.CreateOrderRequest
import org.amogus.ticketsservice.api.responses.OrderInfoResponse
import org.amogus.ticketsservice.domain.interfaces.services.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/orders")
class OrderController : OrderApi {

    @PostMapping
    override suspend fun createOrder(@Valid @RequestBody request: CreateOrderRequest): ResponseEntity<Int> {
        throw NotImplementedError()
    }

    @PostMapping("/{orderId}")
    override suspend fun getOrderInfo(@PathVariable orderId: Int): ResponseEntity<OrderInfoResponse> {
        throw NotImplementedError()
    }

}