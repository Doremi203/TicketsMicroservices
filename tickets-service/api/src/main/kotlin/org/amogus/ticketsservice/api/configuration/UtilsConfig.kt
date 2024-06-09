package org.amogus.ticketsservice.api.configuration

import org.amogus.ticketsservice.bll.DateTimeProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime

@Configuration
class UtilsConfig {
    @Bean
    fun dateTimeProvider(): DateTimeProvider = DateTimeProvider {
        LocalDateTime.now()
    }
}