package org.amogus.authenticationservice.api.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("server")
data class ServerProperties(
    var host: String,
    var protocol: String
)
