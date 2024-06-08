package org.amogus.ticketsservice.data.repositories

import kotlinx.coroutines.flow.toList
import org.amogus.ticketsservice.data.OrdersTable
import org.amogus.ticketsservice.domain.entities.OrderEntityV1
import org.amogus.ticketsservice.domain.interfaces.OrderRepository
import org.amogus.ticketsservice.domain.types.OrderStatus
import org.ufoss.kotysa.PostgresqlR2dbcSqlClient

class PostgresOrderRepository(
    private val dbClient: PostgresqlR2dbcSqlClient
) : OrderRepository {
    override suspend fun createOrder(order: OrderEntityV1): Int {
        return (dbClient insertAndReturn order).id
    }

    override suspend fun getByUserId(userId: Int): List<OrderEntityV1> {
        return (dbClient selectFrom OrdersTable
                where OrdersTable.userId eq userId).fetchAll().toList()
    }

    override suspend fun getByStatus(status: OrderStatus): List<OrderEntityV1> {
        return (dbClient selectFrom OrdersTable
                where OrdersTable.status eq status.value).fetchAll().toList()
    }
}