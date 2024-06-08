package org.amogus.authenticationservice.api.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirements
import org.amogus.authenticationservice.api.requests.AuthenticationRequest
import org.amogus.authenticationservice.api.requests.RegistrationRequest
import org.amogus.authenticationservice.api.responses.AuthenticationResponse
import org.amogus.authenticationservice.api.responses.UserInfoResponse
import org.springframework.http.MediaType
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity

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
interface AuthenticationApi {
    @Operation(
        summary = "Register a new user",
        description = "Register a new user with a nickname, email and password",

        )
    @SecurityRequirements
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "User registered successfully"
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
                description = "User with this email already exists",
                content = [Content(
                    mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                    schema = Schema(implementation = ProblemDetail::class)
                )]
            ),
        ]
    )
    suspend fun register(request: RegistrationRequest): ResponseEntity<AuthenticationResponse>

    @Operation(
        summary = "Login",
        description = "Login with email and password",
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "User logged in successfully",
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
                responseCode = "401",
                description = "Invalid credentials",
                content = [Content(
                    mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                    schema = Schema(implementation = ProblemDetail::class)
                )]
            ),
        ]
    )
    @SecurityRequirements
    suspend fun login(request: AuthenticationRequest): ResponseEntity<AuthenticationResponse>

    @Operation(
        summary = "Get user info",
        description = "Get user info",
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "User info retrieved successfully",
            ),
            ApiResponse(
                responseCode = "401",
                description = "Unauthorized",
                content = [Content(
                    mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                    schema = Schema(implementation = ProblemDetail::class)
                )]
            ),
        ]
    )
    suspend fun getUserInfo(@Parameter(hidden = true) authHeader: String): ResponseEntity<UserInfoResponse>
}