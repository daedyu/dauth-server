package com.b1nd.dauthserver.domain.user.entity

import com.b1nd.dauthserver.domain.user.enumeration.RoleType
import org.springframework.data.annotation.Id
import com.b1nd.dauthserver.domain.user.enumeration.ScopeType
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.relational.core.mapping.Column

@Table("users")
data class UserEntity(
    @field:Id
    val id: Long? = null,
    val dodamId: String,
    @field:Column("fk_client_id")
    val client: String,
    var scopes: List<ScopeType>,
    @field:Column("refresh_token")
    var refreshToken: String,
    val role: RoleType,
) {
    fun updateInfo(refreshToken: String, scopes: List<ScopeType>): UserEntity {
        this.refreshToken = refreshToken
        this.scopes = scopes
        return this
    }
}