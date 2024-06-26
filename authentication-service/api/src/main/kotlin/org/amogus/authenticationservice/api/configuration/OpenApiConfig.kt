package org.amogus.authenticationservice.api.configuration

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import org.amogus.authenticationservice.api.configuration.properties.ServerProperties
import org.springframework.boot.autoconfigure.web.reactive.WebFluxProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class OpenApiConfig(
    private val settings: ServerProperties,
    private val webFluxProperties: WebFluxProperties
) {
    val schemeName = "bearerAuth"

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(Info().title("Authentication-service").version("v1"))
            .servers(
                listOf(
                    Server().url("${settings.protocol}://${settings.host}${webFluxProperties.basePath}")
                )
            )
            .addSecurityItem(SecurityRequirement().addList(schemeName))
            .components(
                Components()
                    .addSecuritySchemes(
                        schemeName,
                        SecurityScheme()
                            .name(schemeName)
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT")
                            .`in`(SecurityScheme.In.HEADER)
                    )
            )
    }
}