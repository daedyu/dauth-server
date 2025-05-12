package com.b1nd.dauthserver.domain.user.enumeration

enum class RoleType(val accessLevel: Int) {
    STUDENT(1),
    PARENT(2),
    TEACHER(3),
    ADMIN(4);
}