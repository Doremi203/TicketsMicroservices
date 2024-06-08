package org.amogus.ticketsservice.api.configuration

import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions.*
import org.amogus.ticketsservice.data.OrdersTable
import org.amogus.ticketsservice.data.StationsTable
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.ufoss.kotysa.PostgresqlR2dbcSqlClient
import org.ufoss.kotysa.r2dbc.coSqlClient
import org.ufoss.kotysa.tables


@Configuration
@EnableConfigurationProperties(DBSettings::class)
class ApplicationConfig(
    private val dbSettings: DBSettings
) {
    @Bean
    fun connectionFactory(): ConnectionFactory = ConnectionFactories.get(
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
    fun dbClient(): PostgresqlR2dbcSqlClient {
        return connectionFactory().coSqlClient(tables().postgresql(StationsTable, OrdersTable))
    }
}