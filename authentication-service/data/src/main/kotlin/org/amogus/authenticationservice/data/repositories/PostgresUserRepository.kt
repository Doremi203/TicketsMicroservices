package org.amogus.authenticationservice.data.repositories

import org.amogus.authenticationservice.data.UsersTable
import org.amogus.authenticationservice.domain.entities.UserEntityV1
import org.amogus.authenticationservice.domain.interfaces.repositories.UserRepository
import org.amogus.authenticationservice.domain.types.Email
import org.ufoss.kotysa.PostgresqlR2dbcSqlClient

class PostgresUserRepository(
    private val dbClient: PostgresqlR2dbcSqlClient
) : UserRepository {
    override suspend fun add(user: UserEntityV1): Int {
        return (dbClient insertAndReturn user).id
    }

    override suspend fun getByEmail(email: Email): UserEntityV1? {
        return (dbClient selectFrom UsersTable
                    where UsersTable.email eq email.value).fetchOneOrNull()
    }
}