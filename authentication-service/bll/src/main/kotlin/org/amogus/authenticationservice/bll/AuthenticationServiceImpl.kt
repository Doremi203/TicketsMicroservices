package org.amogus.authenticationservice.bll

import org.amogus.authenticationservice.domain.interfaces.services.AuthenticationService
import org.amogus.authenticationservice.domain.interfaces.services.JwtService
import org.amogus.authenticationservice.domain.interfaces.services.UserService
import org.amogus.authenticationservice.domain.models.AuthenticationResult
import org.amogus.authenticationservice.domain.models.Credentials
import org.amogus.authenticationservice.domain.models.RegistrationData
import org.amogus.authenticationservice.domain.models.User
import org.amogus.authenticationservice.domain.types.Password
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDateTime

class AuthenticationServiceImpl(
    private val userService: UserService,
    private val jwtService: JwtService,
    private val authenticationManager: ReactiveAuthenticationManager,
    private val passwordEncoder: PasswordEncoder
) : AuthenticationService {
    override suspend fun register(registrationData: RegistrationData): AuthenticationResult {
        val user = User(
            nickname = registrationData.nickname,
            email = registrationData.email,
            password = Password(passwordEncoder.encode(registrationData.password.value)),
            created = LocalDateTime.now(),
        )
        val userId = userService.add(user)

        val jwtToken = jwtService.generateToken(
            user.copy(id = userId)
        )

        return AuthenticationResult(jwtToken)
    }

    override suspend fun login(credentials: Credentials): AuthenticationResult {
        val user = userService.getByEmail(credentials.email)

        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                credentials.email.value,
                credentials.password.value
            )
        )
        val jwtToken = jwtService.generateToken(user)

        return AuthenticationResult(jwtToken)
    }

}