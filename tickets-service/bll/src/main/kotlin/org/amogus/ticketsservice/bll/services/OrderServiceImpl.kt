package org.amogus.ticketsservice.bll.services

import io.r2dbc.spi.R2dbcDataIntegrityViolationException
import org.amogus.ticketsservice.bll.utils.DateTimeProvider
import org.amogus.ticketsservice.domain.exceptions.OrderNotFoundException
import org.amogus.ticketsservice.domain.exceptions.StationNotFoundException
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
    override suspend fun create(model: CreateOrderModel): OrderId {
        val order = Order(
            id = OrderId(0),
            userId = model.userId,
            fromStationId = model.fromStationId,
            toStationId = model.toStationId,
            status = OrderStatus.CHECK,
            created = OrderCreationTime(dateTimeProvider.now())
        )

        return try {
            orderRepository.create(order)
        } catch (e: R2dbcDataIntegrityViolationException) {
            throw StationNotFoundException("One of the stations does not exist")
        }
    }

    override suspend fun getOrderInfo(id: OrderId, userId: UserId): OrderInfo {
        val orderInfo = orderInfoRepository.getByUserId(id, userId)
            ?: throw OrderNotFoundException(id, userId)

        return orderInfo
    }

    override suspend fun handleCheckOrders() {
        orderRepository.updateCheckOrders()
    }
}