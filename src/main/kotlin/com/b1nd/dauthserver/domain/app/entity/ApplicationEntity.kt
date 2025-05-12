package com.b1nd.dauthserver.domain.app.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("applications")
data class ApplicationEntity(
    @Id
    val id: Long? = null,
    val name: String,
    @Column("owner_id")
    val ownerId: String,
    @Column("client_id")
    val clientId: String,
    @Column("client_secret")
    val clientSecret: String,
    val url: String,
    @Column("redirect_url")
    val redirectUrl: String,
    @Column("is_public")
    val isPublic: Boolean
)