package org.amogus.authenticationservice.data.repositories

import org.amogus.authenticationservice.data.UsersTable
import org.amogus.authenticationservice.domain.entities.UserEntityV1
import org.amogus.authenticationservice.domain.interfaces.repositories.UserRepository
import org.amogus.authenticationservice.domain.types.Email
import org.ufoss.kotysa.NoResultException
import org.ufoss.kotysa.PostgresqlR2dbcSqlClient

class PostgresUserRepository(
    private val dbClient: PostgresqlR2dbcSqlClient
) : UserRepository {
    override suspend fun add(users: List<UserEntityV1>): List<Int> {
        return (dbClient insertAndReturn users).map { it.id }
    }

    override suspend fun getByEmail(email: Email): UserEntityV1? {
        return try {
            (dbClient selectFrom UsersTable
                    where UsersTable.nickname eq email.value).fetchFirst()
        }
        catch (_: NoResultException) {
            null
        }
    }
}