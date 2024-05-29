package org.amogus.authenticationservice.domain.models

import org.amogus.authenticationservice.domain.types.Email
import org.amogus.authenticationservice.domain.types.Nickname
import org.amogus.authenticationservice.domain.types.Password

data class RegistrationData(
    val nickname: Nickname,
    val email: Email,
    val password: Password
)