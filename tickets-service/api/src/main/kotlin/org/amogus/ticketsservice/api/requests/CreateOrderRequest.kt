package org.amogus.ticketsservice.api.requests

data class CreateOrderRequest(
    val fromStationId: Int,
    val toStationId: Int
)
