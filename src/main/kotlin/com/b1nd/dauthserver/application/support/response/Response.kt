package com.b1nd.dauthserver.application.support.response

import org.springframework.http.HttpStatus

open class Response(
    val status: Int,
    val message: String
) {
    companion object {
        fun of(status: HttpStatus, message: String): Response {
            return Response(status.value(), message)
        }

        fun ok(message: String): Response {
            return Response(HttpStatus.OK.value(), message)
        }

        fun created(message: String): Response {
            return Response(HttpStatus.CREATED.value(), message)
        }

        fun noContent(message: String): Response {
            return Response(HttpStatus.NO_CONTENT.value(), message)
        }
    }
}