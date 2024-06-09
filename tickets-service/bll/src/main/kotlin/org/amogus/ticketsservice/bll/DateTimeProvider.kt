package org.amogus.ticketsservice.bll

import java.time.LocalDateTime

interface DateTimeProvider {
    fun now(): LocalDateTime
}