package org.amogus.authenticationservice.bll

import org.amogus.authenticationservice.domain.interfaces.services.UserService
import org.amogus.authenticationservice.domain.models.User
import org.amogus.authenticationservice.domain.types.Email

class UserServiceImpl : UserService {
    override suspend fun add(user: User): Int {
        TODO("Not yet implemented")
    }

    override suspend fun getByEmail(email: Email): User {
        TODO("Not yet implemented")
    }
}