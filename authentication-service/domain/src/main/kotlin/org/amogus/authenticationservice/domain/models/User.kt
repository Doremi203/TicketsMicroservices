package org.amogus.authenticationservice.domain.models

import org.amogus.authenticationservice.domain.entities.UserEntityV1
import org.amogus.authenticationservice.domain.types.*

data class User(
    val nickname: Nickname,
    val email: Email,
    val password: Password,
    val created: UserCreationTime,
    val id: UserId = UserId(0),
) {
    constructor(userEntity: UserEntityV1) : this(
        nickname = Nickname(userEntity.nickname),
        email = Email(userEntity.email),
        password = Password(userEntity.password),
        created = UserCreationTime(userEntity.created),
        id = UserId(userEntity.id),
    )
}
