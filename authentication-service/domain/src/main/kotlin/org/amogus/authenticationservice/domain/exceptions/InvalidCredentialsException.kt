package org.amogus.authenticationservice.domain.exceptions


class InvalidCredentialsException(cause: Throwable?) :
    RuntimeException("Invalid credentials", cause) {
}