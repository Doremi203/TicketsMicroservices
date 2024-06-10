package org.amogus.authenticationservice.domain.interfaces.services

import org.amogus.authenticationservice.domain.models.AuthenticationResult
import org.amogus.authenticationservice.domain.models.Credentials
import org.amogus.authenticationservice.domain.models.RegistrationData
import org.amogus.authenticationservice.domain.models.UserInfo
import org.amogus.authenticationservice.domain.types.JwtToken

interface AuthenticationService {
    suspend fun register(registrationData: RegistrationData): AuthenticationResult
    suspend fun login(credentials: Credentials): AuthenticationResult
    suspend fun getUserInfo(token: JwtToken): UserInfo
}