package org.amogus.authenticationservice.api.responses

data class UserInfoResponse(
    val nickname: String,
    val email: String,
    val createdAt: String,
)
