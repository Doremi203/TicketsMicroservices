package org.amogus.authenticationservice.domain.interfaces.services

import org.amogus.authenticationservice.domain.models.User
import org.amogus.authenticationservice.domain.types.Email

interface UserService {
    suspend fun add(user: User): Int
    suspend fun getByEmail(email: Email): User
}