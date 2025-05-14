package com.b1nd.dauthserver.application.auth.data

import com.b1nd.dauthserver.domain.user.enumeration.ScopeType
import jakarta.validation.constraints.NotEmpty

data class CreateQrRequest(
    @field:NotEmpty
    val scopes: List<ScopeType>
)
