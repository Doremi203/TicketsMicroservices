package org.amogus.ticketsservice.domain.entities

import org.amogus.ticketsservice.domain.models.Station
import org.amogus.ticketsservice.domain.types.StationId
import org.amogus.ticketsservice.domain.types.StationName

data class StationEntityV1(
    val id: Int,
    val name: String
)

fun StationEntityV1.toStation() = Station(
    id = StationId(id),
    name = StationName(name)
)
