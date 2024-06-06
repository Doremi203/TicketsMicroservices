package org.amogus.authenticationservice.bll.models

import org.amogus.authenticationservice.domain.models.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class UserDetailsImpl(
    val user: User
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf()
    }

    override fun getPassword(): String {
        return user.password.value
    }

    override fun getUsername(): String {
        return user.email.value
    }
}
