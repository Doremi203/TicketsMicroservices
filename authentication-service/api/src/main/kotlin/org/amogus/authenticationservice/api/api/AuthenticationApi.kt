package org.amogus.authenticationservice.api.api

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid
import org.amogus.authenticationservice.api.requests.AuthenticationRequest
import org.amogus.authenticationservice.api.requests.RegistrationRequest
import org.amogus.authenticationservice.api.responses.AuthenticationResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

interface AuthenticationApi {
    suspend fun register(request: RegistrationRequest): ResponseEntity<AuthenticationResponse>
    suspend fun login(request: AuthenticationRequest): ResponseEntity<AuthenticationResponse>
}