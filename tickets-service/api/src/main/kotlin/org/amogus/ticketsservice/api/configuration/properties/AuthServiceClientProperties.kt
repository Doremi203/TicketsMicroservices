package org.amogus.ticketsservice.api.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("auth-service-client")
data class AuthServiceClientProperties(
    val baseUrl: String
)
