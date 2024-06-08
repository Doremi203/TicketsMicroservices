package org.amogus.ticketsservice.domain.interfaces

import org.amogus.ticketsservice.domain.entities.OrderEntityV1
import org.amogus.ticketsservice.domain.types.OrderStatus

interface OrderRepository {
    suspend fun createOrder(order: OrderEntityV1): Int
    suspend fun getByUserId(userId: Int): List<OrderEntityV1>
    suspend fun getByStatus(status: OrderStatus): List<OrderEntityV1>
}