package org.amogus.authenticationservice.data.repositories

import org.amogus.authenticationservice.data.Users
import org.amogus.authenticationservice.domain.interfaces.repositories.UserRepository
import org.amogus.authenticationservice.domain.models.User
import org.ufoss.kotysa.NoResultException
import org.ufoss.kotysa.PostgresqlR2dbcSqlClient

class PostgresUserRepository(
    private val dbClient: PostgresqlR2dbcSqlClient
) : UserRepository {
    override suspend fun add(user: User): Int {
        return (dbClient insertAndReturn user).id
    }

    override suspend fun getByNickname(nickname: String): User? {
        return try {
            (dbClient selectFrom Users
                    where Users.nickname eq nickname).fetchFirst()
        }
        catch (_: NoResultException) {
            null
        }
    }
}