package org.amogus.ticketsservice.data.repositories

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.amogus.ticketsservice.data.StationsTable
import org.amogus.ticketsservice.domain.entities.toStation
import org.amogus.ticketsservice.domain.interfaces.StationRepository
import org.amogus.ticketsservice.domain.models.Station
import org.amogus.ticketsservice.domain.models.toEntity
import org.amogus.ticketsservice.domain.types.StationId
import org.ufoss.kotysa.R2dbcSqlClient

class R2dbcStationRepository(
    private val dbClient: R2dbcSqlClient
) : StationRepository {
    override suspend fun get(limit: Long, offset: Long): List<Station> {
        return (dbClient selectFrom StationsTable
                limit limit offset offset)
            .fetchAll()
            .map { it.toStation() }
            .toList()
    }

    override suspend fun getById(id: StationId): Station? {
        return (dbClient selectFrom StationsTable
                where StationsTable.id eq id.value)
            .fetchOneOrNull()
            ?.toStation()
    }

    override suspend fun add(station: Station): StationId {
        return StationId((dbClient insertAndReturn station.toEntity()).id)
    }

    override suspend fun update(id: StationId, station: Station): Boolean {
        val rows = (dbClient update StationsTable
                set StationsTable.name eq station.name.value
                where StationsTable.id eq id.value).execute()
        return rows != 0L
    }

    override suspend fun delete(id: StationId): Boolean {
        val rows = (dbClient deleteFrom StationsTable
                where StationsTable.id eq id.value).execute()
        return rows != 0L
    }
}