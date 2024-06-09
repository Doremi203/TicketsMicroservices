package org.amogus.ticketsservice.domain.entities

import org.amogus.ticketsservice.domain.modules.Order
import org.amogus.ticketsservice.domain.types.*
import java.time.LocalDateTime

data class OrderEntityV1(
    val id: Int,
    val userId: Int,
    val fromStationId: Int,
    val toStationId: Int,
    val status: Int,
    val created: LocalDateTime
)

fun OrderEntityV1.toOrder() = Order(
    id = OrderId(id),
    userId = UserId(userId),
    fromStationId = StationId(fromStationId),
    toStationId = StationId(toStationId),
    status = OrderStatus.fromInt(status),
    created = OrderCreationTime(created)
)