package org.amogus.ticketsservice.data.repositories

import kotlinx.coroutines.flow.toList
import org.amogus.ticketsservice.data.OrdersTable
import org.amogus.ticketsservice.data.StationsTable
import org.amogus.ticketsservice.domain.interfaces.OrderInfoRepository
import org.amogus.ticketsservice.domain.models.OrderInfo
import org.amogus.ticketsservice.domain.types.*
import org.ufoss.kotysa.R2dbcSqlClient
import org.ufoss.kotysa.get

class R2dbcOrderInfoRepository(
    private val dbClient: R2dbcSqlClient
) : OrderInfoRepository {
    override suspend fun getById(id: OrderId): OrderInfo? {
        return (selectOrderInfo()
                where OrdersTable.id eq id.value)
            .fetchOneOrNull()
    }

    override suspend fun getByUserId(userId: UserId): List<OrderInfo> {
        return (selectOrderInfo()
                where OrdersTable.userId eq userId.value)
            .fetchAll()
            .toList()
    }

    private fun selectOrderInfo() = (dbClient selectAndBuild {
        OrderInfo(
            id = OrderId(it[OrdersTable.id]!!),
            userId = UserId(it[OrdersTable.userId]!!),
            fromStationName = StationName(it[StationsTable["from"].name]!!),
            toStationName = StationName(it[StationsTable["to"].name]!!),
            status = OrderStatus.fromInt(it[OrdersTable.status]!!),
            created = OrderCreationTime(it[OrdersTable.created]!!)
        )
    }
            from OrdersTable
            innerJoin StationsTable `as` "from" on OrdersTable.fromStationId eq StationsTable["from"].id
            innerJoin StationsTable `as` "to" on OrdersTable.toStationId eq StationsTable["to"].id)
}