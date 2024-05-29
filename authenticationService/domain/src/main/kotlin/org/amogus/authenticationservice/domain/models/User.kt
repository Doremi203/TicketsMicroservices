package org.amogus.authenticationservice.domain.models

import java.time.LocalDateTime

data class User(
    val nickname: String,
    val email: String,
    val password: String,
    val created: LocalDateTime,
    val id: Int = 0,
)
