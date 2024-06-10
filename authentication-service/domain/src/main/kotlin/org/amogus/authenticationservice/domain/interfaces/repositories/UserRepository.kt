package org.amogus.authenticationservice.domain.interfaces.repositories

import org.amogus.authenticationservice.domain.entities.UserEntityV1
import org.amogus.authenticationservice.domain.types.Email

interface UserRepository {
    suspend fun add(user: UserEntityV1): Int
    suspend fun getByEmail(email: Email): UserEntityV1?
}