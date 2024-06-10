package org.amogus.ticketsservice.api.configuration

import org.amogus.ticketsservice.bll.services.OrderServiceImpl
import org.amogus.ticketsservice.bll.services.StationServiceImpl
import org.amogus.ticketsservice.bll.utils.DateTimeProvider
import org.amogus.ticketsservice.domain.interfaces.repositories.OrderInfoRepository
import org.amogus.ticketsservice.domain.interfaces.repositories.OrderRepository
import org.amogus.ticketsservice.domain.interfaces.repositories.StationRepository
import org.amogus.ticketsservice.domain.interfaces.services.OrderService
import org.amogus.ticketsservice.domain.interfaces.services.StationService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime

@Configuration
class BllConfig {
    @Bean
    fun stationService(
        stationRepository: StationRepository
    ): StationService = StationServiceImpl(
        stationRepository
    )

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

    @Bean
    fun dateTimeProvider(): DateTimeProvider = DateTimeProvider { LocalDateTime.now() }
}