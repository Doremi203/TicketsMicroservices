package org.amogus.authenticationservice.domain.interfaces.services

import org.amogus.authenticationservice.domain.models.User
import org.amogus.authenticationservice.domain.types.Email
import org.amogus.authenticationservice.domain.types.UserId

interface UserService {
    suspend fun add(user: User): UserId
    suspend fun getByEmail(email: Email): User
}