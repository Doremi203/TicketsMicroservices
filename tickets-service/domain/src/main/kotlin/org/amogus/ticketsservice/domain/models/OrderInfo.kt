package org.amogus.ticketsservice.domain.models

import org.amogus.ticketsservice.domain.types.OrderCreationTime
import org.amogus.ticketsservice.domain.types.OrderId
import org.amogus.ticketsservice.domain.types.OrderStatus
import org.amogus.ticketsservice.domain.types.StationName

data class OrderInfo(
    val id: OrderId,
    val fromStationName: StationName,
    val toStationName: StationName,
    val status: OrderStatus,
    val created: OrderCreationTime
)
