package com.b1nd.dauthserver.infrastructure.client.dodam.data

import com.b1nd.dauthserver.domain.user.enumeration.RoleType

data class DodamLoginResponse(
    val member: Member,
    val accessToken: String,
    val refreshToken: String
) {
    data class Member(
        val id: String,
        val name: String,
        val email: String,
        val role: RoleType,
        val status: ActiveStatus,
        val profileImage: String? = null,
        val phone: String
    )
    enum class ActiveStatus{
        ACTIVE,
        DEACTIVATE
    }
}