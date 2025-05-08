package com.b1nd.dauthserver.domain.user.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("users")
data class UserEntity(
    @Id
    val id: UUID,
    val dodamId: String,
    val client: String
)
