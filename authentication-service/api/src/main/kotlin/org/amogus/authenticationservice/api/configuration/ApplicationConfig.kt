package org.amogus.authenticationservice.api.configuration

import kotlinx.coroutines.reactor.mono
import org.amogus.authenticationservice.api.models.UserDetailsImpl
import org.amogus.authenticationservice.bll.AuthenticationServiceImpl
import org.amogus.authenticationservice.bll.JwtServiceImpl
import org.amogus.authenticationservice.bll.UserServiceImpl
import org.amogus.authenticationservice.domain.interfaces.services.AuthenticationService
import org.amogus.authenticationservice.domain.interfaces.services.JwtService
import org.amogus.authenticationservice.domain.interfaces.services.UserService
import org.amogus.authenticationservice.domain.types.Email
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebFluxSecurity
class ApplicationConfig {
    @Bean
    fun userService(): UserService = UserServiceImpl()

    @Bean
    fun authenticationManager(): ReactiveAuthenticationManager =
        UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService())

    @Bean
    fun authenticationService(): AuthenticationService =
        AuthenticationServiceImpl(
            userService(),
            jwtService(),
            authenticationManager(),
            passwordEncoder()
        )

    @Bean
    fun userDetailsService(): ReactiveUserDetailsService {
        return ReactiveUserDetailsService { email ->
            mono {
                val user = userService().getByEmail(Email(email))
                UserDetailsImpl(user)
            }
        }
    }

    @Bean
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager =
        config.authenticationManager

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun jwtService(): JwtService = JwtServiceImpl(
        "5b9763e66a963ee6b756961c710e8f1b3a637dd6dce5e18e01317a360e186997",
        15
    )
}