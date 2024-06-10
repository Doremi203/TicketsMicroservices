package org.amogus.ticketsservice.domain.models

import org.amogus.ticketsservice.domain.entities.OrderEntityV1
import org.amogus.ticketsservice.domain.types.*

data class Order(
    val id: OrderId,
    val userId: UserId,
    val fromStationId: StationId,
    val toStationId: StationId,
    val status: OrderStatus,
    val created: OrderCreationTime
)

fun Order.toEntity() = OrderEntityV1(
    id = id.value,
    userId = userId.value,
    fromStationId = fromStationId.value,
    toStationId = toStationId.value,
    status = status.value,
    created = created.value
)
