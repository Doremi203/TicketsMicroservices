package org.amogus.authenticationservice.bll.exceptions

import org.springframework.security.core.AuthenticationException

class IllegalJwtTokenException : AuthenticationException {
    constructor(msg: String?, cause: Throwable?) : super(msg, cause)
    constructor(msg: String?) : super(msg)
}
