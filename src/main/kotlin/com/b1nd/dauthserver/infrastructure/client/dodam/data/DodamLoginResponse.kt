package com.b1nd.dauthserver.infrastructure.client.dodam.data

import com.b1nd.dauthserver.domain.user.enumeration.RoleType

data class DodamLoginResponse(
    val member: MemberResponse,
    val accessToken: String,
    val refreshToken: String
)