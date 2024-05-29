package org.amogus.authenticationservice.domain.models

import org.amogus.authenticationservice.domain.types.Email
import org.amogus.authenticationservice.domain.types.Nickname
import org.amogus.authenticationservice.domain.types.Password
import java.time.LocalDateTime

data class User(
    val nickname: Nickname,
    val email: Email,
    val password: Password,
    val created: LocalDateTime,
    val id: Int = 0,
)
