package org.amogus.authenticationservice.api.configuration

import org.amogus.authenticationservice.api.configuration.properties.JwtProperties
import org.amogus.authenticationservice.bll.AuthenticationServiceImpl
import org.amogus.authenticationservice.bll.JwtServiceImpl
import org.amogus.authenticationservice.bll.UserServiceImpl
import org.amogus.authenticationservice.domain.interfaces.repositories.UserRepository
import org.amogus.authenticationservice.domain.interfaces.services.AuthenticationService
import org.amogus.authenticationservice.domain.interfaces.services.JwtService
import org.amogus.authenticationservice.domain.interfaces.services.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class BllConfig {
    @Bean
    fun userService(userRepository: UserRepository): UserService = UserServiceImpl(userRepository)

    @Bean
    fun jwtService(
        jwtSettings: JwtProperties
    ): JwtService = JwtServiceImpl(
        jwtSettings.secret,
        jwtSettings.expirationInMinutes
    )

    @Bean
    fun authenticationService(
        userService: UserService,
        jwtService: JwtService,
        authenticationManager: ReactiveAuthenticationManager,
        passwordEncoder: PasswordEncoder
    ): AuthenticationService =
        AuthenticationServiceImpl(
            userService,
            jwtService,
            authenticationManager,
            passwordEncoder
        )
}