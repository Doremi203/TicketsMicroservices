package org.amogus.ticketsservice.api.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirements
import org.amogus.ticketsservice.api.requests.AddStationRequest
import org.amogus.ticketsservice.api.responses.StationResponse
import org.springframework.http.MediaType
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.web.server.ServerWebExchange

@ApiResponses(
    value = [
        ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = [Content(
                mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                schema = Schema(implementation = ProblemDetail::class)
            )]
        ),
    ]
)
interface StationApi {

    @Operation(
        summary = "Add station",
        description = "Add station to the database"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "Station added"
            ),
            ApiResponse(
                responseCode = "400",
                description = "Invalid request data",
                content = [Content(
                    mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                    schema = Schema(implementation = ProblemDetail::class)
                )]
            ),
            ApiResponse(
                responseCode = "409",
                description = "Station with this name already exists",
                content = [Content(
                    mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                    schema = Schema(implementation = ProblemDetail::class)
                )]
            )
        ]
    )
    @SecurityRequirements
    suspend fun addStation(
        request: AddStationRequest,
        exchange: ServerWebExchange
    ): ResponseEntity<Int>

    @Operation(
        summary = "Get stations",
        description = "Get stations from the database"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Stations retrieved successfully"
            ),
            ApiResponse(
                responseCode = "400",
                description = "Invalid request data",
                content = [Content(
                    mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                    schema = Schema(implementation = ProblemDetail::class)
                )]
            )
        ]
    )
    @SecurityRequirements
    suspend fun getStations(limit: Long, offset: Long): ResponseEntity<List<StationResponse>>
}