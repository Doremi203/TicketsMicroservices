package org.amogus.authenticationservice.api.configuration

import org.amogus.authenticationservice.api.configuration.properties.JwtProperties
import org.amogus.authenticationservice.api.configuration.properties.PasswordProperties
import org.amogus.authenticationservice.api.configuration.properties.ServerProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration


@Configuration
@EnableConfigurationProperties(
    JwtProperties::class,
    ServerProperties::class,
    PasswordProperties::class
)
class ApplicationConfig