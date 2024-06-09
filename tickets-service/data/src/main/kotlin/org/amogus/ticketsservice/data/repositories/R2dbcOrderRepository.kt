package org.amogus.ticketsservice.data.repositories

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.amogus.ticketsservice.data.OrdersTable
import org.amogus.ticketsservice.domain.entities.toOrder
import org.amogus.ticketsservice.domain.interfaces.OrderRepository
import org.amogus.ticketsservice.domain.models.Order
import org.amogus.ticketsservice.domain.models.toEntity
import org.amogus.ticketsservice.domain.types.OrderId
import org.amogus.ticketsservice.domain.types.OrderStatus
import org.amogus.ticketsservice.domain.types.UserId
import org.ufoss.kotysa.R2dbcSqlClient

class R2dbcOrderRepository(
    private val dbClient: R2dbcSqlClient
) : OrderRepository {
    override suspend fun createOrder(order: Order): OrderId {
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
}