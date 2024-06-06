package org.amogus.authenticationservice.domain.exceptions

import org.amogus.authenticationservice.domain.types.Email

class UserNotFoundException : NotFoundException {
    constructor(email: Email) : this(email, null)
    constructor(email: Email, cause: Throwable?) : super("User with id ${email.value} not found", cause)
    constructor(email: Email, cause: Throwable?, enableSuppression: Boolean, writableStackTrace: Boolean) : super(
        "User with id ${email.value} not found",
        cause,
        enableSuppression,
        writableStackTrace
    )
}