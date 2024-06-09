package org.amogus.ticketsservice.api.client.rest.api

import org.amogus.ticketsservice.api.client.rest.models.UserInfo

interface AuthServiceClient {
    suspend fun getAuthenticatedUserInfo(token: String): UserInfo
}