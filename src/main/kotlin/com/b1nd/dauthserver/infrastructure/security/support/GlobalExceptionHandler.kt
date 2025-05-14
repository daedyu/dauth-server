package com.b1nd.dauthserver.infrastructure.security.support

import com.b1nd.dauthserver.application.support.response.ErrorResponse
import com.b1nd.dauthserver.domain.common.exception.BasicException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.support.WebExchangeBindException
import org.springframework.web.reactive.resource.NoResourceFoundException
import org.springframework.web.server.MethodNotAllowedException
import org.springframework.web.server.ServerWebInputException
import org.springframework.web.server.UnsupportedMediaTypeStatusException

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(BasicException::class)
    suspend fun handleBasicException(exception: BasicException): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(exception.statusCode.status)
            .body(ErrorResponse(exception.statusCode.status, exception.statusCode.message))

    @ExceptionHandler(WebExchangeBindException::class)
    suspend fun handleWebExchangeBindException(): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(400)
            .body(ErrorResponse(400, "값은 비어있을 수 없음"))

    @ExceptionHandler(ServerWebInputException::class)
    suspend fun handleHttpMessageNotReadableException(): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(HttpStatus.BAD_REQUEST.value(), "잘못된 요청 형식"))

    @ExceptionHandler(UnsupportedMediaTypeStatusException::class)
    suspend fun handleUnsupportedMediaTypeStatusException(): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
            .body(ErrorResponse(415, "지원하지 않는 미디어 타입"))


    @ExceptionHandler(IllegalArgumentException::class)
    suspend fun handleIllegalArgumentException(): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request"))

    @ExceptionHandler(NoResourceFoundException::class)
    suspend fun handleNoResourceFoundException(): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(HttpStatus.NOT_FOUND.value(), "찾을 수 없는 경로"))

    @ExceptionHandler(MethodNotAllowedException::class)
    suspend fun handleMethodNotAllowedException(): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
            .body(ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), "지원되지 않는 메서드 형식"))

    @ExceptionHandler(Exception::class)
    suspend fun handleRuntimeException(exception: Exception): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error"))
}