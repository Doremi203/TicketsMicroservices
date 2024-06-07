package org.amogus.authenticationservice.api.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("jwt")
data class JwtSettings (
    var secret: String,
    var expirationInMinutes: Int
)