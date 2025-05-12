package com.b1nd.dauthserver.infrastructure.client.dodam.data

import com.b1nd.dauthserver.domain.user.enumeration.RoleType

data class DodamLoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val member: Member
) {
    data class Member(
        val id: String,
        val pw: String,
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