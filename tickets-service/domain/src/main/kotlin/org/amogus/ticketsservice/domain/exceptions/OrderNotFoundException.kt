package org.amogus.ticketsservice.domain.exceptions

import org.amogus.ticketsservice.domain.types.OrderId
import org.amogus.ticketsservice.domain.types.UserId

class OrderNotFoundException : NotFoundException {
    val orderId: OrderId
    val userId: UserId

    constructor(id: OrderId, userId: UserId) : this(id, userId, null)
    constructor(id: OrderId, userId: UserId, cause: Throwable?)
            : super("Order with id: ${id.value} for user with id: ${userId.value} not found", cause) {
        this.orderId = id
        this.userId = userId
    }

    constructor(
        id: OrderId,
        userId: UserId,
        cause: Throwable?,
        enableSuppression: Boolean,
        writableStackTrace: Boolean
    ) : super(
        "Order with id: ${id.value} for user with id: ${userId.value} not found",
        cause,
        enableSuppression,
        writableStackTrace
    ) {
        this.orderId = id
        this.userId = userId
    }
}