package org.amogus.ticketsservice.bll

import org.amogus.ticketsservice.domain.exceptions.OrderNotFoundException
import org.amogus.ticketsservice.domain.interfaces.repositories.OrderInfoRepository
import org.amogus.ticketsservice.domain.interfaces.repositories.OrderRepository
import org.amogus.ticketsservice.domain.interfaces.services.OrderService
import org.amogus.ticketsservice.domain.models.CreateOrderModel
import org.amogus.ticketsservice.domain.models.Order
import org.amogus.ticketsservice.domain.models.OrderInfo
import org.amogus.ticketsservice.domain.types.OrderCreationTime
import org.amogus.ticketsservice.domain.types.OrderId
import org.amogus.ticketsservice.domain.types.OrderStatus
import org.amogus.ticketsservice.domain.types.UserId

class OrderServiceImpl(
    private val dateTimeProvider: DateTimeProvider,
    private val orderRepository: OrderRepository,
    private val orderInfoRepository: OrderInfoRepository
) : OrderService {
    override suspend fun createOrder(model: CreateOrderModel): OrderId {
        val order = Order(
            id = OrderId(0),
            userId = model.userId,
            fromStationId = model.fromStationId,
            toStationId = model.toStationId,
            status = OrderStatus.CHECK,
            created = OrderCreationTime(dateTimeProvider.now())
        )

        return orderRepository.create(order)
    }

    override suspend fun getOrderInfo(id: OrderId, userId: UserId): OrderInfo {
        val orderInfo = orderInfoRepository.getByUserId(id, userId)
            ?: throw OrderNotFoundException(id, userId)

        return orderInfo
    }
}