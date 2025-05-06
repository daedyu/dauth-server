package com.b1nd.dauthserver.infrastructure.adapter.driven.execption

import org.springframework.http.ResponseEntity

open class BasicException(
    private val status: Int,
    override val message: String
): RuntimeException() {
    fun toResponseEntity(): ResponseEntity<BasicException> =
        ResponseEntity.status(this.status).body(this)
}