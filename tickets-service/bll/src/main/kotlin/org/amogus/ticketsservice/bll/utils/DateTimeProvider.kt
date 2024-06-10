package org.amogus.ticketsservice.bll.utils

import java.time.LocalDateTime

fun interface DateTimeProvider {
    fun now(): LocalDateTime
}