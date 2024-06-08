package org.amogus.ticketsservice.domain.entities

import java.time.LocalDateTime

data class OrderEntityV1(
    val id: Int,
    val userId: Int,
    val fromStationId: Int,
    val toStationId: Int,
    val created: LocalDateTime
)
