package org.amogus.authenticationservice.domain.interfaces.services

import org.amogus.authenticationservice.domain.models.User
import org.amogus.authenticationservice.domain.types.Email
import org.amogus.authenticationservice.domain.types.JwtToken

interface JwtService {
    fun isTokenValid(jwtToken: JwtToken, user: User): Boolean
    fun extractTokenFromHeader(header: String): JwtToken
    fun generateToken(user: User): JwtToken
    fun generateToken(extraClaims: Map<String, Any>, user: User): JwtToken
    fun extractEmail(jwtToken: JwtToken): Email
}