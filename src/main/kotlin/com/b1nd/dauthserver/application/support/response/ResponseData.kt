package com.b1nd.dauthserver.application.support.response

import org.springframework.http.HttpStatus

class ResponseData<T>(
    status: Int,
    message: String,
    val data: T
) : Response(status, message) {
    companion object {
        fun <T> of(status: HttpStatus, message: String, data: T): ResponseData<T> {
            return ResponseData(status.value(), message, data)
        }

        fun <T> ok(message: String, data: T): ResponseData<T> {
            return ResponseData(HttpStatus.OK.value(), message, data)
        }

        fun <T> created(message: String, data: T): ResponseData<T> {
            return ResponseData(HttpStatus.CREATED.value(), message, data)
        }
    }
}