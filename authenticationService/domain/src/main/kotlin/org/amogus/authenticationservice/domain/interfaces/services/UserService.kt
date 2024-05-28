package org.amogus.authenticationservice.domain.interfaces.services

import org.amogus.authenticationservice.domain.models.User

interface UserService {
    suspend fun add(user: User): Long
    suspend fun getByUsername(username: String): User
}