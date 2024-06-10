package org.amogus.ticketsservice.api.controllers

import org.amogus.ticketsservice.api.api.StationApi
import org.amogus.ticketsservice.api.requests.AddStationRequest
import org.amogus.ticketsservice.api.responses.StationResponse
import org.amogus.ticketsservice.domain.interfaces.services.StationService
import org.amogus.ticketsservice.domain.models.AddStationModel
import org.amogus.ticketsservice.domain.types.StationName
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ServerWebExchange
import java.net.URI

@RestController
@RequestMapping("/stations")
class StationController(
    private val stationService: StationService
) : StationApi {

    @PostMapping
    override suspend fun addStation(
        @RequestBody
        request: AddStationRequest,
        exchange: ServerWebExchange
    ): ResponseEntity<Int> {
        val id = stationService.add(
            AddStationModel(
                name = StationName(request.name)
            )
        )

        return ResponseEntity.created(URI.create("${exchange.request.uri}${id.value}")).body(id.value)
    }

    @GetMapping
    override suspend fun getStations(
        @RequestParam
        limit: Long,
        @RequestParam
        offset: Long
    ): ResponseEntity<List<StationResponse>> {
        return ResponseEntity.ok(stationService.get(limit, offset).map {
            StationResponse(
                id = it.id.value,
                name = it.name.value
            )
        })
    }

}