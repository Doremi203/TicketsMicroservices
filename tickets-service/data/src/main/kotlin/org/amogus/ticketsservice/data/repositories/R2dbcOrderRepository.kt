package org.amogus.ticketsservice.data.repositories

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.amogus.ticketsservice.data.OrdersTable
import org.amogus.ticketsservice.domain.entities.toOrder
import org.amogus.ticketsservice.domain.interfaces.repositories.OrderRepository
import org.amogus.ticketsservice.domain.models.Order
import org.amogus.ticketsservice.domain.models.toEntity
import org.amogus.ticketsservice.domain.types.OrderId
import org.amogus.ticketsservice.domain.types.OrderStatus
import org.amogus.ticketsservice.domain.types.UserId
import org.ufoss.kotysa.R2dbcSqlClient
import kotlin.random.Random

class R2dbcOrderRepository(
    private val dbClient: R2dbcSqlClient
) : OrderRepository {
    override suspend fun create(order: Order): OrderId {
        return OrderId((dbClient insertAndReturn order.toEntity()).id)
    }

    override suspend fun getByUserId(userId: UserId): List<Order> {
        return (dbClient selectFrom OrdersTable
                where OrdersTable.userId eq userId.value)
            .fetchAll()
            .map { it.toOrder() }
            .toList()
    }

    override suspend fun getByStatus(status: OrderStatus): List<Order> {
        return (dbClient selectFrom OrdersTable
                where OrdersTable.status eq status.value)
            .fetchAll()
            .map { it.toOrder() }
            .toList()
    }

    override suspend fun updateCheckOrders() {
        (dbClient update OrdersTable
                set OrdersTable.status eq Random.nextInt(2, 4)
                where OrdersTable.status eq OrderStatus.CHECK.value
        ).execute()
    }
}