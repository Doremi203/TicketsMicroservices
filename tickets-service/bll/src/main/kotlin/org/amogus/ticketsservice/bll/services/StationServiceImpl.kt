package org.amogus.ticketsservice.bll.services

import org.amogus.ticketsservice.domain.interfaces.repositories.StationRepository
import org.amogus.ticketsservice.domain.interfaces.services.StationService
import org.amogus.ticketsservice.domain.models.AddStationModel
import org.amogus.ticketsservice.domain.models.Station
import org.amogus.ticketsservice.domain.types.StationId

class StationServiceImpl(
    private val stationRepository: StationRepository
) : StationService {
    override suspend fun add(model: AddStationModel): StationId {
        val station = Station(
            id = StationId(0),
            name = model.name
        )

        return stationRepository.add(station)
    }

    override suspend fun get(limit: Long, offset: Long): List<Station> {
        return stationRepository.get(limit, offset)
    }
}