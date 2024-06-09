package org.amogus.ticketsservice.domain.interfaces.repositories

import org.amogus.ticketsservice.domain.models.Station
import org.amogus.ticketsservice.domain.types.StationId

interface StationRepository {
    suspend fun get(limit: Long, offset: Long) : List<Station>
    suspend fun getById(id: StationId): Station?
    suspend fun add(station: Station) : StationId
    suspend fun update(id: StationId, station: Station): Boolean
    suspend fun delete(id: StationId): Boolean
}