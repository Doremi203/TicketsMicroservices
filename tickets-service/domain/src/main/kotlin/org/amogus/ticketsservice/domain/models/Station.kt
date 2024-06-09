package org.amogus.ticketsservice.domain.models

import org.amogus.ticketsservice.domain.types.StationId
import org.amogus.ticketsservice.domain.types.StationName

data class Station(
    val id: StationId,
    val name: StationName
)
