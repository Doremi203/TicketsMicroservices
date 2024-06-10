package org.amogus.authenticationservice.api.filters

import kotlinx.coroutines.reactor.asCoroutineContext
import kotlinx.coroutines.withContext
import org.amogus.authenticationservice.bll.exceptions.IllegalJwtTokenException
import org.amogus.authenticationservice.domain.interfaces.services.JwtService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.web.server.CoWebFilter
import org.springframework.web.server.CoWebFilterChain
import org.springframework.web.server.ServerWebExchange

class JwtAuthenticationFilter(
    private val jwtService: JwtService
) : CoWebFilter() {
    override suspend fun filter(exchange: ServerWebExchange, chain: CoWebFilterChain) {
        val authHeader = exchange.request.headers["Authorization"]?.firstOrNull()
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            chain.filter(exchange)
            return
        }

        val token = jwtService.extractTokenFromHeader(authHeader)
        val email = try {
            jwtService.extractEmail(token)
        } catch (e: IllegalJwtTokenException) {
            chain.filter(exchange)
            return
        }
        val auth = UsernamePasswordAuthenticationToken(email, token, emptyList())

        val securityContext = ReactiveSecurityContextHolder.withAuthentication(auth)
        withContext(securityContext.asCoroutineContext()) {
            chain.filter(exchange)
        }
    }
}