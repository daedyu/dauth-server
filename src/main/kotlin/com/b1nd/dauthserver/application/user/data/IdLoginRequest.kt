package com.b1nd.dauthserver.application.user.data

import com.b1nd.dauthserver.domain.user.enumeration.ScopeType
import com.b1nd.dauthserver.domain.user.entity.UserEntity
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import java.util.*

data class IdLoginRequest(
    @field:NotBlank
    val id: String,
    @field:NotBlank
    val password: String,
    @field:NotBlank
    val clientId: String,
    @field:NotBlank
    val redirectUrl: String,
    @field:NotEmpty
    val scopes: List<ScopeType>
) {
    fun toEntity(refreshToken: String) =
        UserEntity(
            id = UUID.randomUUID(),
            dodamId = id,
            client = clientId,
            scopes = scopes,
            refreshToken = refreshToken
        )
}