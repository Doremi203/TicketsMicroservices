package org.amogus.authenticationservice.domain.interfaces.services

import org.amogus.authenticationservice.domain.models.AuthenticationResult
import org.amogus.authenticationservice.domain.models.Credentials

interface AuthenticationService {
    suspend fun register(credentials: Credentials): AuthenticationResult
    suspend fun login(credentials: Credentials): AuthenticationResult
}