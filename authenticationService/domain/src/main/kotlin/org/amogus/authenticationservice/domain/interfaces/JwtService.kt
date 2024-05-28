package org.amogus.authenticationservice.domain.interfaces

import org.amogus.authenticationservice.domain.models.User

interface JwtService {
    fun isTokenValid(jwtToken: String, user: User): Boolean
    fun generateToken(user: User): String
    fun generateToken(extraClaims: Map<String, Any>, user: User): String
    fun extractUserName(jwtToken: String): String?
}