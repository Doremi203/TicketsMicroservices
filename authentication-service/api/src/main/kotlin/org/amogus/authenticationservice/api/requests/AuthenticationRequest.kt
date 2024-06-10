package org.amogus.authenticationservice.api.requests

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class AuthenticationRequest(
    @get:NotBlank(message = "Email is required")
    @get:Email(message = "Invalid email")
    val email: String,

    @get:NotBlank(message = "Password is required")
    val password: String,
)
