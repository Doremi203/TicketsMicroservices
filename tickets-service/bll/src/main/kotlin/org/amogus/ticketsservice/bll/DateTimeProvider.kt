package org.amogus.ticketsservice.bll

import java.time.LocalDateTime

fun interface DateTimeProvider {
    fun now(): LocalDateTime
}