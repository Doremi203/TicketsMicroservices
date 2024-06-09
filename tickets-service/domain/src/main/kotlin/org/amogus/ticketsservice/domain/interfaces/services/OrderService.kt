package org.amogus.ticketsservice.domain.interfaces.services

import org.amogus.ticketsservice.domain.models.CreateOrderModel
import org.amogus.ticketsservice.domain.models.OrderInfo
import org.amogus.ticketsservice.domain.types.OrderId
import org.amogus.ticketsservice.domain.types.UserId

interface OrderService {
    suspend fun create(model: CreateOrderModel): OrderId
    suspend fun getOrderInfo(id: OrderId, userId: UserId): OrderInfo
}