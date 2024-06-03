package org.amogus.authenticationservice.domain.entities

import java.time.LocalDateTime

data class UserEntityV1(
    val nickname: String,
    val email: String,
    val password: String,
    val created: LocalDateTime,
    val id: Int = 0,
)
