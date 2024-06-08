package org.amogus.ticketsservice.domain.entities

import org.amogus.ticketsservice.domain.types.OrderStatus
import java.time.LocalDateTime

data class OrderEntityV1(
    val id: Int,
    val userId: Int,
    val fromStationId: Int,
    val toStationId: Int,
    val status: Int,
    val created: LocalDateTime
)
