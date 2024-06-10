package org.amogus.authenticationservice.api.controllers

import org.amogus.authenticationservice.domain.exceptions.DuplicateEmailException
import org.amogus.authenticationservice.domain.exceptions.InvalidCredentialsException
import org.amogus.authenticationservice.domain.exceptions.NotFoundException
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.support.WebExchangeBindException
import org.springframework.web.reactive.resource.NoResourceFoundException
import org.springframework.web.server.ServerWebExchange
import java.net.URI

@ControllerAdvice
@Order(1)
class CustomExceptionHandler {
    @ExceptionHandler(NotFoundException::class)
    suspend fun handleNotFoundException(e: NotFoundException): ProblemDetail {
        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.message ?: "Resource not found")
        problemDetail.type = URI.create("https://datatracker.ietf.org/doc/html/rfc9110#section-15.5.5")

        return problemDetail
    }

    @ExceptionHandler(WebExchangeBindException::class)
    suspend fun handleWebExchangeBindException(
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
    suspend fun handleInvalidCredentialsException(e: InvalidCredentialsException): ProblemDetail {
        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, "Invalid email or password.")
        problemDetail.type = URI.create("https://datatracker.ietf.org/doc/html/rfc9110#section-15.5.2")

        return problemDetail
    }

    @ExceptionHandler(DuplicateEmailException::class)
    suspend fun handleDuplicateEmailException(e: DuplicateEmailException): ProblemDetail {
        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, "Email: ${e.email.value} is already taken.")
        problemDetail.type = URI.create("https://datatracker.ietf.org/doc/html/rfc9110#section-15.5.10")

        return problemDetail
    }

    @ExceptionHandler(NoResourceFoundException::class)
    suspend fun handleNoResourceFoundException(e: NoResourceFoundException): ProblemDetail {
        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "Resource not found.")
        problemDetail.type = URI.create("https://datatracker.ietf.org/doc/html/rfc9110#section-15.5.5")

        return problemDetail
    }
}