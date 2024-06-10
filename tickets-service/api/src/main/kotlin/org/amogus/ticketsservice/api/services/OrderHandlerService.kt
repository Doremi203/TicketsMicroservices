package org.amogus.ticketsservice.api.services

interface OrderHandlerService {
    suspend fun handleOrders()
}