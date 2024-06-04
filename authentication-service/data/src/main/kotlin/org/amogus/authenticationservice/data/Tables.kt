package org.amogus.authenticationservice.data

import org.amogus.authenticationservice.domain.entities.UserEntityV1
import org.ufoss.kotysa.postgresql.PostgresqlTable

object UsersTable : PostgresqlTable<UserEntityV1>("users") {
    val id = integer(UserEntityV1::id).identity().primaryKey()
    val nickname = varchar(UserEntityV1::nickname, "nickname", 50)
    val email = varchar(UserEntityV1::email, "email", 100).unique()
    val password = varchar(UserEntityV1::password, "password", 255)
    val created = timestamp(UserEntityV1::created)
}