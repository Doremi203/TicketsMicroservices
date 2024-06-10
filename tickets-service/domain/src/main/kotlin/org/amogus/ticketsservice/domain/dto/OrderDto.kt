package org.amogus.ticketsservice.domain.dto

import org.amogus.ticketsservice.domain.models.OrderInfo
import org.amogus.ticketsservice.domain.types.OrderCreationTime
import org.amogus.ticketsservice.domain.types.OrderId
import org.amogus.ticketsservice.domain.types.OrderStatus
import org.amogus.ticketsservice.domain.types.StationName
import java.time.LocalDateTime

data class OrderDto(
    val id: Int,
    val fromStationName: String,
    val toStationName: String,
    val status: Int,
    val created: LocalDateTime
)

fun OrderDto.toOrderInfo() = OrderInfo(
    id = OrderId(id),
    fromStationName = StationName(fromStationName),
    toStationName = StationName(toStationName),
    status = OrderStatus.fromInt(status),
    created = OrderCreationTime(created)
)
