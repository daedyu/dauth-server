package com.b1nd.dauthserver.domain.user.entity

import java.util.UUID
import org.springframework.data.annotation.Id
import com.b1nd.dauthserver.domain.user.enumeration.ScopeType
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.relational.core.mapping.Column

@Table("users")
data class UserEntity(
    @Id
    val id: UUID? = null,
    val dodamId: String,
    @Column("client_id")
    val client: String,
    val scopes: List<ScopeType>,
    @Column("refresh_token")
    val refreshToken: String
) {
    fun updateInfo(refreshToken: String, scopes: List<ScopeType>): UserEntity =
        this.copy(refreshToken = refreshToken, scopes = scopes)
}