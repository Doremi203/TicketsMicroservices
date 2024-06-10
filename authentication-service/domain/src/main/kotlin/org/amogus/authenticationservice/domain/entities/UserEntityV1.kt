package org.amogus.authenticationservice.domain.entities

import org.amogus.authenticationservice.domain.models.User
import java.time.LocalDateTime

data class UserEntityV1(
    val nickname: String,
    val email: String,
    val password: String,
    val created: LocalDateTime,
    val id: Int = 0,
) {
    constructor(user: User) : this(
        user.nickname.value,
        user.email.value,
        user.password.value,
        user.created.value,
        user.id.value,
    )
}
