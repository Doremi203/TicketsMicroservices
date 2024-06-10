package org.amogus.ticketsservice.api.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("server")
data class ServerSettings(
    var host: String,
    var protocol: String
)
