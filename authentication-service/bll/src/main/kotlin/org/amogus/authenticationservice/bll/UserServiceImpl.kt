package org.amogus.authenticationservice.bll

import org.amogus.authenticationservice.domain.exceptions.UserNotFoundException
import org.amogus.authenticationservice.domain.interfaces.repositories.UserRepository
import org.amogus.authenticationservice.domain.interfaces.services.UserService
import org.amogus.authenticationservice.domain.models.User
import org.amogus.authenticationservice.domain.types.Email

class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {
    override suspend fun add(user: User): Int {
        TODO("Not yet implemented")
    }

    override suspend fun getByEmail(email: Email): User {
        val entity = userRepository.getByEmail(email) ?: throw UserNotFoundException(email)
        return User(entity)
    }
}