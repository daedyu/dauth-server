package com.b1nd.dauthserver.domain.auth.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("authentication_words")
data class AuthenticationWordEntity(
    @Id
    val id: Long? = null,
    val word: String
)