package org.amogus.authenticationservice.domain.interfaces

import org.amogus.authenticationservice.domain.models.AuthenticationResult
import org.amogus.authenticationservice.domain.models.Credentials

interface AuthenticationService {
    fun register(request: Credentials): AuthenticationResult?
    fun login(request: Credentials): AuthenticationResult?
}