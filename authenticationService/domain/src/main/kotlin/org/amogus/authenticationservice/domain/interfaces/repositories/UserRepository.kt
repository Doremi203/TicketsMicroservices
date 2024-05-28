package org.amogus.authenticationservice.domain.interfaces.repositories

import org.amogus.authenticationservice.domain.models.User

interface UserRepository {
    suspend fun add(user: User): Long
    suspend fun getByUserName(username: String): User?
}