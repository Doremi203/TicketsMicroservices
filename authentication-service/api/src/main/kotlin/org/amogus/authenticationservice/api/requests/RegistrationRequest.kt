package org.amogus.authenticationservice.api.requests

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.amogus.authenticationservice.api.requests.validators.ValidPassword

data class RegistrationRequest(
    @get:NotBlank(message = "Nickname is required")
    val nickname: String,

    @get:NotBlank(message = "Email is required")
    @get:Email(message = "Invalid email")
    val email: String,

    @get:NotBlank(message = "Password is required")
    @get:ValidPassword
    val password: String,
)
