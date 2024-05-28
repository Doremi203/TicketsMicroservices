package org.amogus.authenticationservice.domain.interfaces

import org.amogus.authenticationservice.api.requests.AuthenticationRequest
import org.amogus.authenticationservice.api.responses.AuthenticationResponse

interface AuthenticationService {
    fun register(request: AuthenticationRequest): AuthenticationResponse?
    fun login(request: AuthenticationRequest): AuthenticationResponse?
}