package org.amogus.ticketsservice.domain.interfaces.repositories

import org.amogus.ticketsservice.domain.models.Order
import org.amogus.ticketsservice.domain.types.OrderId
import org.amogus.ticketsservice.domain.types.OrderStatus
import org.amogus.ticketsservice.domain.types.UserId

interface OrderRepository {
    suspend fun create(order: Order): OrderId
    suspend fun getByUserId(userId: UserId): List<Order>
    suspend fun getByStatus(status: OrderStatus): List<Order>
    suspend fun updateCheckOrders()
}