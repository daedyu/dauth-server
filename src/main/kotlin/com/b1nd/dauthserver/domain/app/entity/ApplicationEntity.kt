package com.b1nd.dauthserver.domain.app.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("applications")
data class ApplicationEntity(
    @Id
    val id: Long? = null,
    val name: String,
    val ownerId: String,
    val clientId: String,
    val clientSecret: String,
    val url: String,
    val redirectUrl: String,
    val isPublic: Boolean
)