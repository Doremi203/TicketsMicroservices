package org.amogus.authenticationservice.services.interfaces

import org.amogus.authenticationservice.requests.AuthenticationRequest
import org.amogus.authenticationservice.responses.AuthenticationResponse

interface AuthenticationService {
    fun register(request: AuthenticationRequest): AuthenticationResponse?
    fun login(request: AuthenticationRequest): AuthenticationResponse?
}