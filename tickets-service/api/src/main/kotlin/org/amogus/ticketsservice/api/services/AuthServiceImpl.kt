package org.amogus.ticketsservice.api.services

import org.amogus.ticketsservice.api.client.rest.api.AuthServiceClient
import org.amogus.ticketsservice.api.exceptions.AuthException
import org.amogus.ticketsservice.domain.types.UserId
import org.springframework.web.server.ServerWebExchange

class AuthServiceImpl(
    private val authServiceClient: AuthServiceClient
) : AuthService {
    override suspend fun authorize(exchange: ServerWebExchange): UserId {
        val token = exchange.request.headers["Authorization"]?.firstOrNull()
            ?: throw AuthException("Authorization header is missing")

        return authServiceClient.getAuthenticatedUserId(token)
    }
}