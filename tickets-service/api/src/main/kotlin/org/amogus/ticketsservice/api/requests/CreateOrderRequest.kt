package org.amogus.ticketsservice.api.requests

import jakarta.validation.constraints.Min

data class CreateOrderRequest(
    @get:Min(1, message = "From station id should be greater than 0")
    val fromStationId: Int,
    @get:Min(1, message = "To station id should be greater than 0")
    val toStationId: Int
)
