package org.amogus.ticketsservice.api.client.rest.services

import kotlinx.coroutines.reactor.awaitSingle
import org.amogus.ticketsservice.api.client.rest.api.AuthServiceClient
import org.amogus.ticketsservice.api.client.rest.models.UserInfo
import org.springframework.web.reactive.function.client.WebClient

class SpringWebAuthService(
    private val webClient: WebClient,
) : AuthServiceClient {
    override suspend fun getAuthenticatedUserInfo(token: String): UserInfo {
        return webClient.get()
            .uri("/user-info")
            .header("Authorization", token)
            .retrieve()
            .bodyToMono(UserInfo::class.java)
            .awaitSingle()
    }
}