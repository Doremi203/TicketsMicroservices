package org.amogus.ticketsservice.domain.types

enum class OrderStatus(val value: Int) {
    CHECK(1),
    SUCCESS(2),
    REJECTED(3);

    companion object {
        fun fromInt(status: Int) = when(status) {
            1 -> CHECK
            2 -> SUCCESS
            3 -> REJECTED
            else -> throw IllegalArgumentException("Unknown status: $status")
        }
    }
}