package org.amogus.authenticationservice.domain.interfaces.services

import org.amogus.authenticationservice.domain.models.User
import org.amogus.authenticationservice.domain.types.Email

interface JwtService {
    fun isTokenValid(jwtToken: String, user: User): Boolean
    fun generateToken(user: User): String
    fun generateToken(extraClaims: Map<String, Any>, user: User): String
    fun extractEmail(jwtToken: String): Email
}