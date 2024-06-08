package org.amogus.authenticationservice.api.controllers

import org.amogus.authenticationservice.domain.exceptions.DuplicateEmailException
import org.amogus.authenticationservice.domain.exceptions.InvalidCredentialsException
import org.amogus.authenticationservice.domain.exceptions.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.support.WebExchangeBindException
import org.springframework.web.server.ServerWebExchange
import java.net.URI

@ControllerAdvice
class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(e: NotFoundException): ProblemDetail {
        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.message ?: "Resource not found")
        problemDetail.type = URI.create("https://datatracker.ietf.org/doc/html/rfc9110#section-15.5.5")

        return problemDetail
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
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

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidCredentialsException::class)
    fun handleInvalidCredentialsException(e: InvalidCredentialsException): ProblemDetail {
        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, "Invalid email or password.")
        problemDetail.type = URI.create("https://datatracker.ietf.org/doc/html/rfc9110#section-15.5.2")

        return problemDetail
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateEmailException::class)
    fun handleDuplicateEmailException(e: DuplicateEmailException): ProblemDetail {
        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, "Email: ${e.email.value} is already taken.")
        problemDetail.type = URI.create("https://datatracker.ietf.org/doc/html/rfc9110#section-15.5.10")

        return problemDetail
    }
}