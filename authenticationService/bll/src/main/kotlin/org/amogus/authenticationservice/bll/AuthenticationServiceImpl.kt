package org.amogus.authenticationservice.bll

import org.amogus.authenticationservice
import org.amogus.authenticationservice.repositories.interfaces.UserRepository
import org.amogus.authenticationservice.api.requests.AuthenticationRequest
import org.amogus.authenticationservice.api.responses.AuthenticationResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl(
    private val userDetailsService: UserDetailsService,
    private val userRepository: UserRepository,
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager,
    private val passwordEncoder: PasswordEncoder
) : AuthenticationService {
    override fun register(request: AuthenticationRequest): AuthenticationResponse? {
        val user = User(
            username = request.username,
            password = passwordEncoder.encode(request.password),
            role = Role.valueOf(request.role)
        )
        val userId = userRepository.add(user)

        val jwtToken = jwtService.generateToken(
            user.copy(id = userId)
        )

        return AuthenticationResponse(jwtToken)
    }

    override fun login(request: AuthenticationRequest): AuthenticationResponse? {
        val user = userDetailsService.loadUserByUsername(request.username)

        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.username,
                request.password
            )
        )
        val jwtToken = jwtService.generateToken(user)

        return AuthenticationResponse(jwtToken)
    }

}