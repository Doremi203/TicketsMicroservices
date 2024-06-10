package org.amogus.ticketsservice.api.client.rest.api

import org.amogus.ticketsservice.domain.types.UserId

interface AuthServiceClient {
    suspend fun getAuthenticatedUserId(token: String): UserId
}