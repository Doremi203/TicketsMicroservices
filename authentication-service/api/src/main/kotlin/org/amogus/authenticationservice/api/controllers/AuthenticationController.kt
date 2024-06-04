package org.amogus.authenticationservice.api.controllers

import jakarta.validation.Valid
import org.amogus.authenticationservice.api.api.AuthenticationApi
import org.amogus.authenticationservice.api.requests.AuthenticationRequest
import org.amogus.authenticationservice.api.requests.RegistrationRequest
import org.amogus.authenticationservice.api.responses.AuthenticationResponse
import org.amogus.authenticationservice.domain.interfaces.services.AuthenticationService
import org.amogus.authenticationservice.domain.models.Credentials
import org.amogus.authenticationservice.domain.models.RegistrationData
import org.amogus.authenticationservice.domain.types.Email
import org.amogus.authenticationservice.domain.types.Nickname
import org.amogus.authenticationservice.domain.types.Password
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthenticationController(
    private val authenticationService: AuthenticationService
) : AuthenticationApi {
    @PostMapping("/register")
    override suspend fun register(@Valid @RequestBody request: RegistrationRequest): ResponseEntity<AuthenticationResponse> {
        return ResponseEntity.ok(
            AuthenticationResponse(
                authenticationService.register(
                    RegistrationData(
                        Nickname(request.nickname),
                        Email(request.email),
                        Password(request.password),
                    )
                ).token
            )
        )
    }

    @PostMapping("/login")
    override suspend fun login(@Valid @RequestBody request: AuthenticationRequest): ResponseEntity<AuthenticationResponse> {
        return ResponseEntity.ok(
            AuthenticationResponse(
                authenticationService.login(
                    Credentials(
                        Email(request.email),
                        Password(request.password),
                    )
                ).token
            )
        )
    }
}