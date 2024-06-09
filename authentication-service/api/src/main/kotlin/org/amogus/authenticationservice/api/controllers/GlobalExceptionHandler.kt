package org.amogus.authenticationservice.api.controllers

import org.amogus.authenticationservice.domain.exceptions.DuplicateEmailException
import org.amogus.authenticationservice.domain.exceptions.InvalidCredentialsException
import org.amogus.authenticationservice.domain.exceptions.NotFoundException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.support.WebExchangeBindException
import org.springframework.web.reactive.resource.NoResourceFoundException
import org.springframework.web.server.ServerWebExchange
import java.net.URI

@ControllerAdvice
class GlobalExceptionHandler {
    val logger: Logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(e: NotFoundException): ProblemDetail {
        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.message ?: "Resource not found")
        problemDetail.type = URI.create("https://datatracker.ietf.org/doc/html/rfc9110#section-15.5.5")

        return problemDetail
    }

    @ExceptionHandler(WebExchangeBindException::class)
    fun handleWebExchangeBindException(
        ex: WebExchangeBindException,
        exchange: ServerWebExchange
    ): ProblemDetail {
        val fieldErrors = ex.fieldErrors.associate { error ->
            error.field to error.defaultMessage
        }

        val globalErrors = ex.globalErrors.associate { error ->
            error.objectName to error.defaultMessage
        }

        val errors = fieldErrors + globalErrors

        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Your request parameters didn't validate.")
        problemDetail.properties = mapOf("errors" to errors)
        problemDetail.type = URI.create("https://datatracker.ietf.org/doc/html/rfc9110#section-15.5.1")

        return problemDetail
    }

    @ExceptionHandler(InvalidCredentialsException::class)
    fun handleInvalidCredentialsException(e: InvalidCredentialsException): ProblemDetail {
        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, "Invalid email or password.")
        problemDetail.type = URI.create("https://datatracker.ietf.org/doc/html/rfc9110#section-15.5.2")

        return problemDetail
    }

    @ExceptionHandler(DuplicateEmailException::class)
    fun handleDuplicateEmailException(e: DuplicateEmailException): ProblemDetail {
        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, "Email: ${e.email.value} is already taken.")
        problemDetail.type = URI.create("https://datatracker.ietf.org/doc/html/rfc9110#section-15.5.10")

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