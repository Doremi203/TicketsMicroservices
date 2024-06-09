package org.amogus.ticketsservice.domain.interfaces

import org.amogus.ticketsservice.domain.models.OrderInfo
import org.amogus.ticketsservice.domain.types.UserId

interface OrderInfoRepository {
    suspend fun getByUserId(userId: UserId): List<OrderInfo>
}