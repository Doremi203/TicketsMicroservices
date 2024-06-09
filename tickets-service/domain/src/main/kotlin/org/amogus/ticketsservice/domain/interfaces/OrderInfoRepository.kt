package org.amogus.ticketsservice.domain.interfaces

import org.amogus.ticketsservice.domain.models.OrderInfo
import org.amogus.ticketsservice.domain.types.OrderId
import org.amogus.ticketsservice.domain.types.UserId

interface OrderInfoRepository {
    suspend fun getById(id: OrderId): OrderInfo?
    suspend fun getByUserId(userId: UserId): List<OrderInfo>
}