package com.b1nd.dauthserver.domain.user.repository

import com.b1nd.dauthserver.domain.user.entity.UserEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.UUID

interface UserRepository: CoroutineCrudRepository<UserEntity, UUID> {
    suspend fun findByDodamId(dodamId: String): UserEntity?
    suspend fun findByDodamIdAndClient(dodamId: String, client: String): UserEntity?
}