package org.amogus.ticketsservice.domain.interfaces.services

import org.amogus.ticketsservice.domain.models.AddStationModel
import org.amogus.ticketsservice.domain.models.Station
import org.amogus.ticketsservice.domain.types.StationId

interface StationService {
    suspend fun add(model: AddStationModel): StationId
    suspend fun get(limit: Long, offset: Long): List<Station>
}