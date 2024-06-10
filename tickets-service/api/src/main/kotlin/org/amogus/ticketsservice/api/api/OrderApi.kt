package org.amogus.ticketsservice.api.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.amogus.ticketsservice.api.requests.CreateOrderRequest
import org.amogus.ticketsservice.api.responses.OrderInfoResponse
import org.springframework.http.MediaType
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.web.server.ServerWebExchange

@ApiResponses(
    value = [
        ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = [Content(
                mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                schema = Schema(implementation = ProblemDetail::class)
            )]
        ),
    ]
)
interface OrderApi {

    @Operation(
        summary = "Create a new order",
        description = "Create a new order for ticket from one station to another",
    )
    suspend fun createOrder(
        body: CreateOrderRequest,
        exchange: ServerWebExchange
    ): ResponseEntity<Int>
    suspend fun getOrderInfo(orderId: Int): ResponseEntity<OrderInfoResponse>
}