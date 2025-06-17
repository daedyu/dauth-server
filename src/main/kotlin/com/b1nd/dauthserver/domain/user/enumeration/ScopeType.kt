package com.b1nd.dauthserver.domain.user.enumeration

enum class ScopeType(val value: String) {
    OPENID("openid"),
    PHONE("phone"),
    READ_PROFILE("read:profile"),
    READ_DORMITORY("read:dormitory"),
    WRITE_DORMITORY("write:dormitory"),
    READ_OUTSLEEP("read:outsleep"),
    READ_CLUB("read:club");

    companion object {
        fun fromValue(value: String): ScopeType =
            entries.first { it.value == value }
    }
}