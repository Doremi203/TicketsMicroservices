package org.amogus.ticketsservice.api.configuration

import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions.*
import org.amogus.ticketsservice.api.configuration.properties.DBProperties
import org.amogus.ticketsservice.data.OrdersTable
import org.amogus.ticketsservice.data.StationsTable
import org.amogus.ticketsservice.data.repositories.R2dbcOrderInfoRepository
import org.amogus.ticketsservice.data.repositories.R2dbcOrderRepository
import org.amogus.ticketsservice.data.repositories.R2dbcStationRepository
import org.amogus.ticketsservice.domain.interfaces.repositories.OrderInfoRepository
import org.amogus.ticketsservice.domain.interfaces.repositories.OrderRepository
import org.amogus.ticketsservice.domain.interfaces.repositories.StationRepository
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.ufoss.kotysa.R2dbcSqlClient
import org.ufoss.kotysa.r2dbc.coSqlClient
import org.ufoss.kotysa.tables

@Configuration
@EnableConfigurationProperties(DBProperties::class)
class DataConfig {
    @Bean
    fun connectionFactory(dbSettings: DBProperties): ConnectionFactory = ConnectionFactories.get(
        builder()
            .option(DRIVER, "pool")
            .option(PROTOCOL, "postgresql")
            .option(HOST, dbSettings.host)
            .option(PORT, dbSettings.port)
            .option(USER, dbSettings.user)
            .option(PASSWORD, dbSettings.password)
            .option(DATABASE, dbSettings.database)
            .build()
    )

    @Bean
    fun dbClient(connectionFactory: ConnectionFactory): R2dbcSqlClient {
        return connectionFactory.coSqlClient(tables().postgresql(StationsTable, OrdersTable))
    }

    @Bean
    fun stationRepository(dbClient: R2dbcSqlClient): StationRepository = R2dbcStationRepository(dbClient)

    @Bean
    fun orderRepository(dbClient: R2dbcSqlClient): OrderRepository = R2dbcOrderRepository(dbClient)

    @Bean
    fun orderInfoRepository(dbClient: R2dbcSqlClient): OrderInfoRepository = R2dbcOrderInfoRepository(dbClient)
}