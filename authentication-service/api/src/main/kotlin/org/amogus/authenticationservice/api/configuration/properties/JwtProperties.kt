package org.amogus.authenticationservice.api.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("jwt")
data class JwtProperties (
    var secret: String,
    var expirationInMinutes: Int
)