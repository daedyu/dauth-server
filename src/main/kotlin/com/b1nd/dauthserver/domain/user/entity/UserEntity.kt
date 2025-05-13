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
    val role: RoleType,
) {
    fun updateInfo(scopes: List<ScopeType>): UserEntity {
        this.scopes = scopes
        return this
    }
}