package org.amogus.authenticationservice.responses

data class AuthenticationResponse(
    val authenticationToken: String,
    val expirationTime: Long
)
