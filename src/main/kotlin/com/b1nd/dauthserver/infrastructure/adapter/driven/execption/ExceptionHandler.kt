package com.b1nd.dauthserver.infrastructure.adapter.driven.execption

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(value = [BasicException::class])
    fun basicExceptionHandler(e: BasicException): ResponseEntity<BasicException> {
        return e.toResponseEntity();
    }
}