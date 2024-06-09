package org.amogus.ticketsservice.api.configuration

import org.amogus.ticketsservice.api.client.rest.api.AuthServiceClient
import org.amogus.ticketsservice.api.client.rest.services.SpringWebAuthService
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient


@Configuration
@EnableConfigurationProperties(AuthServiceClientSettings::class)
class ApplicationConfig {
    @Bean
    fun webClient(settings: AuthServiceClientSettings): WebClient {
        return WebClient.builder()
            .baseUrl(settings.baseUrl)
            .build()
    }

    @Bean
    fun authServiceClient(webClient: WebClient): AuthServiceClient = SpringWebAuthService(webClient)
}