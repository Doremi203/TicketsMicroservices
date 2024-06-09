package org.amogus.ticketsservice.domain.models

import org.amogus.ticketsservice.domain.types.*

data class OrderInfo(
    val id: OrderId,
    val userId: UserId,
    val fromStationName: StationName,
    val toStationName: StationName,
    val status: OrderStatus,
    val created: OrderCreationTime
)
