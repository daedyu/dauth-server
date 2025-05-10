package com.b1nd.dauthserver.infrastructure.client.dodam.data

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
        val role: MemberRole,
        val status: ActiveStatus,
        val profileImage: String? = null,
        val phone: String
    )
    enum class MemberRole{
        STUDENT,
        PARENT,
        TEACHER,
        ADMIN;
    }
    enum class ActiveStatus{
        ACTIVE,
        DEACTIVATE
    }
}