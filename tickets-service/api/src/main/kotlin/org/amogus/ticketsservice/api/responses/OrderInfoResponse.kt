package org.amogus.ticketsservice.api.responses

import java.time.LocalDateTime

data class OrderInfoResponse(
    val id: Int,
    val fromStationName: String,
    val toStationName: String,
    val status: String,
    val created: LocalDateTime
)
