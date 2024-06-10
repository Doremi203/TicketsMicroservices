package org.amogus.ticketsservice.domain.exceptions

import org.amogus.ticketsservice.domain.types.StationId

class StationNotFoundException : NotFoundException {
    constructor(message: String) : super(message)
    constructor(id: StationId) : this(id, null)
    constructor(id: StationId, cause: Throwable?) : super("Station with id: ${id.value} not found", cause)
    constructor(id: StationId, cause: Throwable?, enableSuppression: Boolean, writableStackTrace: Boolean) : super(
        "Station with id: ${id.value} not found",
        cause,
        enableSuppression,
        writableStackTrace
    )
}