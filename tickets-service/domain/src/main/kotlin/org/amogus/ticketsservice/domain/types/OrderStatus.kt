package org.amogus.ticketsservice.domain.types

enum class OrderStatus(val value: Int) {
    CHECK(1),
    SUCCESS(2),
    REJECTED(3)
}