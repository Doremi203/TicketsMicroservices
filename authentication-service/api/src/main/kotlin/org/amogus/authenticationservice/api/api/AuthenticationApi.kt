package org.amogus.authenticationservice.api.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirements
import org.amogus.authenticationservice.api.requests.AuthenticationRequest
import org.amogus.authenticationservice.api.requests.RegistrationRequest
import org.amogus.authenticationservice.api.responses.AuthenticationResponse
import org.amogus.authenticationservice.api.responses.UserInfoResponse
import org.springframework.http.ResponseEntity

interface AuthenticationApi {
    @Operation(
        summary = "Register a new user",
        description = "Register a new user with a nickname, email and password",

        )
    @SecurityRequirements
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "User registered successfully"),
            ApiResponse(responseCode = "400", description = "Invalid request data"),
            ApiResponse(responseCode = "409", description = "User with this email already exists"),
        ]
    )
    suspend fun register(request: RegistrationRequest): ResponseEntity<AuthenticationResponse>

    @Operation(
        summary = "Login",
        description = "Login with email and password",
    )
    @SecurityRequirements
    suspend fun login(request: AuthenticationRequest): ResponseEntity<AuthenticationResponse>

    suspend fun getUserInfo(@Parameter(hidden = true) authHeader: String): ResponseEntity<UserInfoResponse>
}