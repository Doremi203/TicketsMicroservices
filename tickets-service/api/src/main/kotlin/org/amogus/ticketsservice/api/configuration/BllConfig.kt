package org.amogus.ticketsservice.api.configuration

import org.amogus.ticketsservice.bll.utils.DateTimeProvider
import org.amogus.ticketsservice.bll.services.OrderServiceImpl
import org.amogus.ticketsservice.domain.interfaces.repositories.OrderInfoRepository
import org.amogus.ticketsservice.domain.interfaces.repositories.OrderRepository
import org.amogus.ticketsservice.domain.interfaces.services.OrderService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BllConfig {
    @Bean
    fun orderService(
        dateTimeProvider: DateTimeProvider,
        orderRepository: OrderRepository,
        orderInfoRepository: OrderInfoRepository
    ): OrderService = OrderServiceImpl(
        dateTimeProvider,
        orderRepository,
        orderInfoRepository
    )
}