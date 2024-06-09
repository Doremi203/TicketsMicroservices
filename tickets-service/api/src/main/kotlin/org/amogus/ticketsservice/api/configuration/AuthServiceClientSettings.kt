package org.amogus.ticketsservice.api.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("auth-service-client")
data class AuthServiceClientSettings(
    val baseUrl: String
)
