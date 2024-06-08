package org.amogus.authenticationservice.bll

import io.r2dbc.spi.R2dbcDataIntegrityViolationException
import org.amogus.authenticationservice.domain.entities.UserEntityV1
import org.amogus.authenticationservice.domain.exceptions.DuplicateEmailException
import org.amogus.authenticationservice.domain.exceptions.UserNotFoundException
import org.amogus.authenticationservice.domain.interfaces.repositories.UserRepository
import org.amogus.authenticationservice.domain.interfaces.services.UserService
import org.amogus.authenticationservice.domain.models.User
import org.amogus.authenticationservice.domain.types.Email
import org.amogus.authenticationservice.domain.types.UserId

class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {
    override suspend fun add(user: User): UserId {
        return try {
            UserId(userRepository.add(UserEntityV1(user)))
        } catch (e: R2dbcDataIntegrityViolationException) {
            throw DuplicateEmailException(user.email)
        }
    }

    override suspend fun getByEmail(email: Email): User {
        val entity = userRepository.getByEmail(email) ?: throw UserNotFoundException(email)
        return User(entity)
    }
}