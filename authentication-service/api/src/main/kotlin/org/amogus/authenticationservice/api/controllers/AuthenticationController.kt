package org.amogus.authenticationservice.api.controllers

import jakarta.validation.Valid
import org.amogus.authenticationservice.api.api.AuthenticationApi
import org.amogus.authenticationservice.api.requests.AuthenticationRequest
import org.amogus.authenticationservice.api.requests.RegistrationRequest
import org.amogus.authenticationservice.api.responses.AuthenticationResponse
import org.amogus.authenticationservice.api.responses.UserInfoResponse
import org.amogus.authenticationservice.domain.interfaces.services.AuthenticationService
import org.amogus.authenticationservice.domain.interfaces.services.JwtService
import org.amogus.authenticationservice.domain.models.Credentials
import org.amogus.authenticationservice.domain.models.RegistrationData
import org.amogus.authenticationservice.domain.types.Email
import org.amogus.authenticationservice.domain.types.Nickname
import org.amogus.authenticationservice.domain.types.Password
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthenticationController(
    private val authenticationService: AuthenticationService,
    private val jwtService: JwtService
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

    @GetMapping("/user-info")
    override suspend fun getUserInfo(@RequestHeader("Authorization") authHeader: String): ResponseEntity<UserInfoResponse> {
        val userInfo = authenticationService.getUserInfo(jwtService.extractTokenFromHeader(authHeader))

        return ResponseEntity.ok(
            UserInfoResponse(
                userInfo.nickname.value,
                userInfo.email.value,
                userInfo.createdAt.value.toString(),
            )
        )
    }
}