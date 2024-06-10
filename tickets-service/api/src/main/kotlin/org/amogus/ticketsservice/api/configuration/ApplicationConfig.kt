package org.amogus.ticketsservice.api.configuration

import org.amogus.ticketsservice.api.client.rest.api.AuthServiceClient
import org.amogus.ticketsservice.api.client.rest.services.SpringWebAuthService
import org.amogus.ticketsservice.api.configuration.properties.AuthServiceClientProperties
import org.amogus.ticketsservice.api.configuration.properties.ServerProperties
import org.amogus.ticketsservice.api.services.AuthService
import org.amogus.ticketsservice.api.services.AuthServiceImpl
import org.amogus.ticketsservice.api.services.OrderHandlerService
import org.amogus.ticketsservice.api.services.OrderHandlerServiceImpl
import org.amogus.ticketsservice.domain.interfaces.services.OrderService
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient


@Configuration
@EnableConfigurationProperties(AuthServiceClientProperties::class, ServerProperties::class)
class ApplicationConfig {
    @Bean
    fun webClient(settings: AuthServiceClientProperties): WebClient {
        return WebClient.builder()
            .baseUrl(settings.baseUrl)
            .build()
    }

    @Bean
    fun orderHandlerService(orderService: OrderService): OrderHandlerService = OrderHandlerServiceImpl(orderService)

    @Bean
    fun authServiceClient(webClient: WebClient): AuthServiceClient = SpringWebAuthService(webClient)

    @Bean
    fun authService(client: AuthServiceClient): AuthService = AuthServiceImpl(client)
}