package org.amogus.authenticationservice.api.api

import org.amogus.authenticationservice.api.requests.AuthenticationRequest
import org.amogus.authenticationservice.api.requests.RegistrationRequest
import org.amogus.authenticationservice.api.responses.AuthenticationResponse
import org.amogus.authenticationservice.api.responses.UserInfoResponse
import org.springframework.http.ResponseEntity

interface AuthenticationApi {
    suspend fun register(request: RegistrationRequest): ResponseEntity<AuthenticationResponse>
    suspend fun login(request: AuthenticationRequest): ResponseEntity<AuthenticationResponse>
    suspend fun getUserInfo(token: String): ResponseEntity<UserInfoResponse>
}