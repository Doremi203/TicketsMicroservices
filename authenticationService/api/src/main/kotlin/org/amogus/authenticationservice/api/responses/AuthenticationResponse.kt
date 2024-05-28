package org.amogus.authenticationservice.api.responses

data class AuthenticationResponse(
    val authenticationToken: String,
    val expirationTime: Long
)
