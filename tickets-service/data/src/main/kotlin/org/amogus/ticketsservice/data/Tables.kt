package org.amogus.ticketsservice.data

import org.amogus.ticketsservice.domain.entities.StationEntityV1
import org.ufoss.kotysa.postgresql.PostgresqlTable

object StationsTable : PostgresqlTable<StationEntityV1>("stations") {
    val id = integer(StationEntityV1::id).identity().primaryKey()
    val name = varchar(StationEntityV1::name, "name", 50)
}