package org.amogus.ticketsservice.api.controllers

import org.amogus.ticketsservice.api.exceptions.AuthException
import org.amogus.ticketsservice.domain.exceptions.OrderNotFoundException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.reactive.resource.NoResourceFoundException
import java.net.URI

@ControllerAdvice
class GlobalExceptionHandler {
    val logger: Logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(AuthException::class)
    fun handleAuthException(e: AuthException): ProblemDetail {
        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, "Authorization failed.")
        problemDetail.type = URI.create("https://datatracker.ietf.org/doc/html/rfc9110#section-15.5.2")

        return problemDetail
    }

    @ExceptionHandler(OrderNotFoundException::class)
    fun handleOrderNotFoundException(e: OrderNotFoundException): ProblemDetail {
        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "You don't have order with id ${e.orderId}.")
        problemDetail.type = URI.create("https://datatracker.ietf.org/doc/html/rfc9110#section-15.5.5")

        return problemDetail
    }

    @ExceptionHandler(NoResourceFoundException::class)
    fun handleNoResourceFoundException(e: NoResourceFoundException): ProblemDetail {
        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "Resource not found.")
        problemDetail.type = URI.create("https://datatracker.ietf.org/doc/html/rfc9110#section-15.5.5")

        return problemDetail
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ProblemDetail {
        logger.error("Internal server error", e)
        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error.")
        problemDetail.type = URI.create("https://datatracker.ietf.org/doc/html/rfc9110#section-15.6.1")

        return problemDetail
    }
}