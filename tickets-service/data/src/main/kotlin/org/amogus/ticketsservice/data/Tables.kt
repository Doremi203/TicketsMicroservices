package org.amogus.ticketsservice.data

import org.amogus.ticketsservice.domain.entities.OrderEntityV1
import org.amogus.ticketsservice.domain.entities.StationEntityV1
import org.ufoss.kotysa.postgresql.PostgresqlTable

object StationsTable : PostgresqlTable<StationEntityV1>("stations") {
    val id = integer(StationEntityV1::id).identity().primaryKey()
    val name = varchar(StationEntityV1::name, "name", 50)
}

object OrdersTable : PostgresqlTable<OrderEntityV1>("orders") {
    val id = integer(OrderEntityV1::id).identity().primaryKey()
    val userId = integer(OrderEntityV1::userId)
    val fromStationId = integer(OrderEntityV1::fromStationId)
    val toStationId = integer(OrderEntityV1::toStationId)
    val status = integer(OrderEntityV1::status)
    val created = timestamp(OrderEntityV1::created)
}