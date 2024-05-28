package org.amogus.authenticationservice.requests

import jakarta.validation.constraints.NotBlank

data class AuthenticationRequest(
    @get:NotBlank(message = "Username is required")
    val username: String,
    @get:NotBlank(message = "Password is required")
    val password: String,
)
