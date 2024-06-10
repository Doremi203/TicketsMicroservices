package org.amogus.ticketsservice.api.requests

import jakarta.validation.constraints.NotBlank

data class AddStationRequest(
    @get:NotBlank(message = "Name is required")
    val name: String
)
