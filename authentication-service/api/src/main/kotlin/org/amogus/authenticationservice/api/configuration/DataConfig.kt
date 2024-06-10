package org.amogus.authenticationservice.api.configuration

import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions.*
import org.amogus.authenticationservice.api.configuration.properties.DBProperties
import org.amogus.authenticationservice.data.UsersTable
import org.amogus.authenticationservice.data.repositories.PostgresUserRepository
import org.amogus.authenticationservice.domain.interfaces.repositories.UserRepository
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.ufoss.kotysa.PostgresqlR2dbcSqlClient
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
    fun dbClient(connectionFactory: ConnectionFactory): PostgresqlR2dbcSqlClient {
        return connectionFactory.coSqlClient(tables().postgresql(UsersTable))
    }

    @Bean
    fun userRepository(dbClient: PostgresqlR2dbcSqlClient): UserRepository = PostgresUserRepository(dbClient)
}