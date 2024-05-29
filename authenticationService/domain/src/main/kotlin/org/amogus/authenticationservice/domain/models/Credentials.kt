package org.amogus.authenticationservice.domain.models

import org.amogus.authenticationservice.domain.types.Email
import org.amogus.authenticationservice.domain.types.Password

data class Credentials(
    val email: Email,
    val password: Password
)
