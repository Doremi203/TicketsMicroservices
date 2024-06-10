package org.amogus.ticketsservice.data.repositories

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.amogus.ticketsservice.data.OrdersTable
import org.amogus.ticketsservice.data.StationsTable
import org.amogus.ticketsservice.domain.dto.OrderDto
import org.amogus.ticketsservice.domain.dto.toOrderInfo
import org.amogus.ticketsservice.domain.interfaces.repositories.OrderInfoRepository
import org.amogus.ticketsservice.domain.models.OrderInfo
import org.amogus.ticketsservice.domain.types.*
import org.ufoss.kotysa.R2dbcSqlClient
import org.ufoss.kotysa.get

class R2dbcOrderInfoRepository(
    private val dbClient: R2dbcSqlClient
) : OrderInfoRepository {
    override suspend fun getByUserId(id: OrderId, userId: UserId): OrderInfo? {
        return (selectOrderInfo()
                where OrdersTable.id eq id.value
                and OrdersTable.userId eq userId.value)
            .fetchOneOrNull()
            ?.toOrderInfo()
    }

    override suspend fun getAllByUserId(userId: UserId): List<OrderInfo> {
        return (selectOrderInfo()
                where OrdersTable.userId eq userId.value)
            .fetchAll()
            .map { it.toOrderInfo() }
            .toList()
    }

    private fun selectOrderInfo() = (dbClient selectAndBuild {
        OrderDto(
            id = it[OrdersTable.id]!!,
            fromStationName = it[StationsTable["fr"].name]!!,
            toStationName = it[StationsTable["t"].name]!!,
            status = it[OrdersTable.status]!!,
            created = it[OrdersTable.created]!!
        )
    }
            from OrdersTable
            innerJoin StationsTable `as` "fr" on OrdersTable.fromStationId eq StationsTable["fr"].id
            innerJoin StationsTable `as` "t" on OrdersTable.toStationId eq StationsTable["t"].id)
}