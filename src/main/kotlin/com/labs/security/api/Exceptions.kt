package com.labs.security.api

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.servlet.http.HttpServletResponse
import javax.validation.ConstraintViolationException

sealed class ApiException(msg: String, open val code: Int) : RuntimeException(msg)

class NotFoundException(msg: String, code: Int = HttpStatus.NOT_FOUND.value()) : ApiException(msg, code)
class UserAlreadyExistsException(msg: String, code: Int = HttpStatus.BAD_REQUEST.value()) : ApiException(msg, code)
class UnauthorizedException(msg: String, code: Int = HttpStatus.UNAUTHORIZED.value()) : ApiException(msg, code)

@ControllerAdvice
class DefaultExceptionHandler: ResponseEntityExceptionHandler() {

  @ExceptionHandler(value = [ApiException::class])
  fun onApiException(ex: ApiException, response: HttpServletResponse): Unit =
    response.sendError(ex.code, ex.message)

  @ExceptionHandler(value = [NotImplementedError::class])
  fun onNotImplemented(ex: NotImplementedError, response: HttpServletResponse): Unit =
    response.sendError(HttpStatus.NOT_IMPLEMENTED.value())

  @ExceptionHandler(value = [ConstraintViolationException::class])
  fun onConstraintViolation(ex: ConstraintViolationException, response: HttpServletResponse): Unit =
    response.sendError(HttpStatus.BAD_REQUEST.value(), ex.constraintViolations.joinToString(", ") { it.message })
}
