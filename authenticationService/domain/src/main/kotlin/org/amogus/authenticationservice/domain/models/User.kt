package org.amogus.authenticationservice.domain.models

data class User(
    private val username: String,
    private val password: String,
    val id: Long = 0L,
)
