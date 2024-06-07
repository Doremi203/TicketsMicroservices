package org.amogus.authenticationservice.api.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("db")
data class DBSettings(
    val host: String,
    val port: Int,
    val user: String,
    val password: String,
    val database: String
)