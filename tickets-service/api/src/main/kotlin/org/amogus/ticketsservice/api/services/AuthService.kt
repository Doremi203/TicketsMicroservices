package org.amogus.ticketsservice.api.services

import org.amogus.ticketsservice.domain.types.UserId
import org.springframework.web.server.ServerWebExchange

interface AuthService {
    suspend fun authorize(exchange: ServerWebExchange): UserId
}