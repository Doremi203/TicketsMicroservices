package org.amogus.ticketsservice.domain.models

import org.amogus.ticketsservice.domain.types.StationId
import org.amogus.ticketsservice.domain.types.UserId

data class CreateOrderModel(
    val userId: UserId,
    val fromStationId: StationId,
    val toStationId: StationId
)
