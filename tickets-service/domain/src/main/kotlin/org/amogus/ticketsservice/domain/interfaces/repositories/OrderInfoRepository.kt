package org.amogus.ticketsservice.domain.interfaces.repositories

import org.amogus.ticketsservice.domain.models.OrderInfo
import org.amogus.ticketsservice.domain.types.OrderId
import org.amogus.ticketsservice.domain.types.UserId

interface OrderInfoRepository {
    suspend fun getByUserId(id: OrderId, userId: UserId): OrderInfo?
    suspend fun getAllByUserId(userId: UserId): List<OrderInfo>
}