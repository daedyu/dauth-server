package com.b1nd.dauthserver.domain.app.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("applications")
data class ApplicationEntity(
    @Id
    val id: Long? = null,
    var name: String,
    @Column("owner_id")
    var ownerId: String,
    @Column("client_id")
    val clientId: String,
    @Column("client_secret")
    val clientSecret: String,
    var url: String,
    @Column("redirect_url")
    var redirectUrl: String,
    @Column("is_public")
    var isPublic: Boolean
) {
    fun updateOwner(ownerId: String) {
        this.ownerId = ownerId
    }

    fun updateInfo(name: String?, url: String?, redirectUrl: String?, isPublic: Boolean?) {
        if (name != null) this.name = name
        if (url != null) this.url = url
        if (redirectUrl != null) this.redirectUrl = redirectUrl
        if (isPublic != null) this.isPublic = isPublic
    }
}