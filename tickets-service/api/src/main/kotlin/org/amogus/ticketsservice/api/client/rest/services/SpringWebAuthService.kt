package org.amogus.ticketsservice.api.client.rest.services

import kotlinx.coroutines.reactor.awaitSingle
import org.amogus.ticketsservice.api.client.rest.api.AuthServiceClient
import org.amogus.ticketsservice.api.client.rest.models.UserInfo
import org.amogus.ticketsservice.api.exceptions.AuthException
import org.amogus.ticketsservice.api.exceptions.ServiceUnavailableException
import org.amogus.ticketsservice.domain.types.UserId
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException

class SpringWebAuthService(
    private val webClient: WebClient,
) : AuthServiceClient {
    override suspend fun getAuthenticatedUserId(token: String): UserId {
        return try {
            UserId(
                webClient.get()
                    .uri("/user-info")
                    .header("Authorization", token)
                    .retrieve()
                    .bodyToMono(UserInfo::class.java)
                    .awaitSingle()
                    .userId
            )
        }
        catch (e: WebClientResponseException) {
            when (e.statusCode) {
                HttpStatus.UNAUTHORIZED -> throw AuthException("Unauthorized")
                else -> throw ServiceUnavailableException("Service unavailable because of external auth service error")
            }
        }
    }
}