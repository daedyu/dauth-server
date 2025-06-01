package com.b1nd.dauthserver.domain.user.repository

import com.b1nd.dauthserver.domain.user.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface UserRepository: CoroutineCrudRepository<UserEntity, Long> {
    suspend fun findByDodamId(dodamId: String): UserEntity?
    suspend fun findByDodamIdAndClient(dodamId: String, client: String): UserEntity?
    suspend fun countByClientIn(clients: List<String>): Long
}