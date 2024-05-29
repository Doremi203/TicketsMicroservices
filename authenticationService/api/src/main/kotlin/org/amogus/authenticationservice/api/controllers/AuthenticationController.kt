package org.amogus.authenticationservice.api.controllers

import jakarta.validation.Valid
import org.amogus.authenticationservice.api.requests.AuthenticationRequest
import org.amogus.authenticationservice.api.responses.AuthenticationResponse
import org.amogus.authenticationservice.domain.interfaces.services.AuthenticationService
import org.amogus.authenticationservice.domain.models.Credentials
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthenticationController(
    private val authenticationService: AuthenticationService
) {
    @PostMapping("/register")
    suspend fun register(@Valid @RequestBody request: AuthenticationRequest): ResponseEntity<AuthenticationResponse> {
        return ResponseEntity.ok(
            AuthenticationResponse(
                authenticationService.register(
                    Credentials(request.username, request.password)
                ).token
            )
        )
    }

    @PostMapping("/login")
    suspend fun login(@Valid @RequestBody request: AuthenticationRequest): ResponseEntity<AuthenticationResponse> {
        return ResponseEntity.ok(
            AuthenticationResponse(
                authenticationService.login(
                    Credentials(request.username, request.password)
                ).token
            )
        )
    }
}