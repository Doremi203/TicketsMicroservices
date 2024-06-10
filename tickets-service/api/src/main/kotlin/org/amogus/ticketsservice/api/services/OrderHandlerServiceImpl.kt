package org.amogus.ticketsservice.api.services

import org.amogus.ticketsservice.domain.interfaces.services.OrderService
import org.springframework.scheduling.annotation.Scheduled

class OrderHandlerServiceImpl(
    private val orderService: OrderService
) : OrderHandlerService {
    @Scheduled(fixedRate = 10000)
    override suspend fun handleOrders() {
        orderService.handleCheckOrders()
    }
}