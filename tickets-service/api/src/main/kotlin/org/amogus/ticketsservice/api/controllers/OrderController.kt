package org.amogus.ticketsservice.api.controllers

import jakarta.validation.Valid
import org.amogus.ticketsservice.api.api.OrderApi
import org.amogus.ticketsservice.api.requests.CreateOrderRequest
import org.amogus.ticketsservice.api.responses.OrderInfoResponse
import org.amogus.ticketsservice.api.services.AuthService
import org.amogus.ticketsservice.domain.interfaces.services.OrderService
import org.amogus.ticketsservice.domain.models.CreateOrderModel
import org.amogus.ticketsservice.domain.types.OrderId
import org.amogus.ticketsservice.domain.types.StationId
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ServerWebExchange
import java.net.URI

@RestController
@RequestMapping("/orders")
class OrderController(
    private val orderService: OrderService,
    private val authService: AuthService
) : OrderApi {
    val logger: Logger = LoggerFactory.getLogger(OrderController::class.java)

    @PostMapping
    override suspend fun createOrder(
        @Valid
        @RequestBody
        body: CreateOrderRequest,
        exchange: ServerWebExchange
    ): ResponseEntity<Int> {
        val userId = authService.authorize(exchange)
        logger.info("User $userId is creating order from ${body.fromStationId} to ${body.toStationId}")

        val id = orderService.create(
            CreateOrderModel(
                userId = userId,
                fromStationId = StationId(body.fromStationId),
                toStationId = StationId(body.toStationId)
            )
        )

        return ResponseEntity.created(URI.create("${exchange.request.path}${id}")).body(id.value)
    }

    @GetMapping("/{orderId}")
    override suspend fun getOrderInfo(
        @PathVariable orderId: Int,
        exchange: ServerWebExchange
    ): ResponseEntity<OrderInfoResponse> {
        val userId = authService.authorize(exchange)
        logger.info("User $userId is getting order info for order $orderId")

        val orderInfo = orderService.getOrderInfo(OrderId(orderId), userId)

        return ResponseEntity.ok(
            OrderInfoResponse(
                id = orderInfo.id.value,
                fromStationName = orderInfo.fromStationName.value,
                toStationName = orderInfo.toStationName.value,
                status = orderInfo.status.name,
                created = orderInfo.created.value
            )
        )
    }

}