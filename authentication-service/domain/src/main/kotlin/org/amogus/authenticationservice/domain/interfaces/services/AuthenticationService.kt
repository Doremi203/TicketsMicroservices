package org.amogus.authenticationservice.domain.interfaces.services

import org.amogus.authenticationservice.domain.models.AuthenticationResult
import org.amogus.authenticationservice.domain.models.Credentials
import org.amogus.authenticationservice.domain.models.RegistrationData

interface AuthenticationService {
    suspend fun register(registrationData: RegistrationData): AuthenticationResult
    suspend fun login(credentials: Credentials): AuthenticationResult
}