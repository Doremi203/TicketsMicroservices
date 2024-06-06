package org.amogus.authenticationservice.domain.models

import org.amogus.authenticationservice.domain.types.Email
import org.amogus.authenticationservice.domain.types.Nickname
import org.amogus.authenticationservice.domain.types.UserCreationTime

data class UserInfo(
    val nickname: Nickname,
    val email: Email,
    val createdAt: UserCreationTime,
)
