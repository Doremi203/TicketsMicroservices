package org.amogus.authenticationservice.domain.models

data class RegistrationData(
    val nickname: String,
    val email: String,
    val password: String
)