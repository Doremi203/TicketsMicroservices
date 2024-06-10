package org.amogus.ticketsservice.api.api

import org.amogus.ticketsservice.api.requests.AddStationRequest
import org.amogus.ticketsservice.api.responses.StationResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.server.ServerWebExchange

interface StationApi {

    suspend fun addStation(
        request: AddStationRequest,
        exchange: ServerWebExchange
    ): ResponseEntity<Int>

    suspend fun getStations(limit: Long, offset: Long): ResponseEntity<List<StationResponse>>
}