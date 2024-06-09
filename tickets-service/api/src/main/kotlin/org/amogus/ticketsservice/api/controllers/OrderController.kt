package org.amogus.ticketsservice.api.controllers

import jakarta.validation.Valid
import org.amogus.ticketsservice.api.api.OrderApi
import org.amogus.ticketsservice.api.client.rest.api.AuthServiceClient
import org.amogus.ticketsservice.api.requests.CreateOrderRequest
import org.amogus.ticketsservice.api.responses.OrderInfoResponse
import org.amogus.ticketsservice.domain.interfaces.services.OrderService
import org.amogus.ticketsservice.domain.models.CreateOrderModel
import org.amogus.ticketsservice.domain.types.StationId
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ServerWebExchange
import java.net.URI

@RestController
@RequestMapping("/orders")
class OrderController(
    private val orderService: OrderService,
    private val authServiceClient: AuthServiceClient
) : OrderApi {

    @PostMapping
    override suspend fun createOrder(
        @Valid
        @RequestBody
        request: CreateOrderRequest,
        @RequestHeader("Authorization")
        token: String,
        exchange: ServerWebExchange
        ): ResponseEntity<Int> {
        val userId = authServiceClient.getAuthenticatedUserId(token)

        val id = orderService.create(
            CreateOrderModel(
                userId = userId,
                fromStationId = StationId(request.fromStationId),
                toStationId = StationId(request.toStationId)
            )
        )

        return ResponseEntity.created(URI.create("${exchange.request.path}${id}")).body(id.value)
    }

    @GetMapping("/{orderId}")
    override suspend fun getOrderInfo(@PathVariable orderId: Int): ResponseEntity<OrderInfoResponse> {
        throw NotImplementedError()
    }

}