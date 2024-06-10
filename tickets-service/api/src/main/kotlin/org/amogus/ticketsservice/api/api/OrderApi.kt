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
        ApiResponse(
            responseCode = "503",
            description = "Service unavailable",
            content = [Content(
                mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                schema = Schema(implementation = ProblemDetail::class)
            )]
        )
    ]
)
interface OrderApi {

    @Operation(
        summary = "Create a new order",
        description = "Create a new order for ticket from one station to another",
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "Order created",
            ),
            ApiResponse(
                responseCode = "400",
                description = "Bad request",
                content = [Content(
                    mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                    schema = Schema(implementation = ProblemDetail::class)
                )]
            ),
            ApiResponse(
                responseCode = "404",
                description = "Station not found",
                content = [Content(
                    mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                    schema = Schema(implementation = ProblemDetail::class)
                )]
            )
        ]

    )
    suspend fun createOrder(
        body: CreateOrderRequest,
        exchange: ServerWebExchange
    ): ResponseEntity<Int>

    @Operation(
        summary = "Get order info",
        description = "Get information about order by id",
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Order info",
                content = [Content(
                    schema = Schema(implementation = OrderInfoResponse::class)
                )]
            ),
            ApiResponse(
                responseCode = "404",
                description = "Order not found",
                content = [Content(
                    mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                    schema = Schema(implementation = ProblemDetail::class)
                )]
            ),
        ]
    )
    suspend fun getOrderInfo(
        orderId: Int,
        exchange: ServerWebExchange
    ): ResponseEntity<OrderInfoResponse>
}