package org.amogus.authenticationservice.api.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import org.amogus.authenticationservice.api.filters.JwtAuthenticationFilter
import org.amogus.authenticationservice.domain.interfaces.services.JwtService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ProblemDetail
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository
import reactor.core.publisher.Flux
import java.net.URI

@Configuration
@EnableWebFluxSecurity
class SecurityConfig {
    @Bean
    fun springSecurityFilterChain(
        http: ServerHttpSecurity,
        authenticationManager: ReactiveAuthenticationManager,
        jwtService: JwtService,
        objectMapper: ObjectMapper
    ): SecurityWebFilterChain {
        val jwtAuthenticationFilter = JwtAuthenticationFilter(jwtService)
        return http
            .authenticationManager(authenticationManager)
            .csrf { it.disable() }
            .httpBasic { it.disable() }
            .formLogin { it.disable() }
            .logout { it.disable() }
            .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
            .authorizeExchange {
                it.pathMatchers("/user-info").authenticated()
                it.anyExchange().permitAll()
            }
            .addFilterAt(jwtAuthenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            .exceptionHandling {
                it.authenticationEntryPoint { exchange, ex ->
                    exchange.response.statusCode = HttpStatus.UNAUTHORIZED
                    val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.message)
                    problemDetail.type = URI.create("https://datatracker.ietf.org/doc/html/rfc9110#section-15.5.2")
                    problemDetail.instance = URI.create(exchange.request.path.value())
                    val buffer = exchange.response.bufferFactory().wrap(objectMapper.writeValueAsString(problemDetail).toByteArray())
                    exchange.response.headers.contentType = MediaType.APPLICATION_PROBLEM_JSON
                    exchange.response.writeWith(Flux.just(buffer))
                }
            }
            .build()
    }
}