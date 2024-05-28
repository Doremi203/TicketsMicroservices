package org.amogus.authenticationservice.domain.models

data class User(
    val username: String,
    val password: String,
    val id: Long = 0L,
)
