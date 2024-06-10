package org.amogus.authenticationservice.api.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "password")
data class PasswordProperties(
    val minLength: Int,
    val maxLength: Int,
    val minUpperCase: Int,
    val minLowerCase: Int,
    val minDigit: Int,
    val minSpecial: Int,
)
