package org.amogus.authenticationservice.bll

import org.amogus.authenticationservice.domain.interfaces.services.AuthenticationService
import org.amogus.authenticationservice.domain.interfaces.services.JwtService
import org.amogus.authenticationservice.domain.interfaces.services.UserService
import org.amogus.authenticationservice.domain.models.AuthenticationResult
import org.amogus.authenticationservice.domain.models.Credentials
import org.amogus.authenticationservice.domain.models.User
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl(
    private val userService: UserService,
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager,
    private val passwordEncoder: PasswordEncoder
) : AuthenticationService {
    override suspend fun register(credentials: Credentials): AuthenticationResult? {
        val user = User(
            username = credentials.username,
            password = passwordEncoder.encode(credentials.password)
        )
        val userId = userService.add(user)

        val jwtToken = jwtService.generateToken(
            user.copy(id = userId)
        )

        return AuthenticationResult(jwtToken)
    }

    override suspend fun login(credentials: Credentials): AuthenticationResult? {
        val user = userService.getByUsername(credentials.username)

        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                credentials.username,
                credentials.password
            )
        )
        val jwtToken = jwtService.generateToken(user)

        return AuthenticationResult(jwtToken)
    }

}