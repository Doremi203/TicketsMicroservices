package org.amogus.authenticationservice.domain.interfaces.repositories

import org.amogus.authenticationservice.domain.models.User
import org.amogus.authenticationservice.domain.types.Email

interface UserRepository {
    suspend fun add(user: User): Int
    suspend fun getByEmail(email: Email): User?
}