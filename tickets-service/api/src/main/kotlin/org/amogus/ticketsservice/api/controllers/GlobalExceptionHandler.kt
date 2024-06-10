package org.amogus.ticketsservice.api.controllers

import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler
import java.net.URI

@ControllerAdvice
@Order(2)
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ProblemDetail {
        logger.error("Internal server error", e)
        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error.")
        problemDetail.type = URI.create("https://datatracker.ietf.org/doc/html/rfc9110#section-15.6.1")

        return problemDetail
    }
}