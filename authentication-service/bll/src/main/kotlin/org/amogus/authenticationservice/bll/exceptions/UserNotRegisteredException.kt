package org.amogus.authenticationservice.bll.exceptions

import org.springframework.security.core.AuthenticationException

class UserNotRegisteredException : AuthenticationException {
    constructor(cause: Throwable?) : super("User not registered", cause)
    constructor() : super("User not registered")
}
