package com.b1nd.dauthserver.domain.app.repository

import kotlinx.coroutines.flow.Flow
import com.b1nd.dauthserver.domain.app.entity.ApplicationEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface ApplicationRepository: CoroutineCrudRepository<ApplicationEntity, Long> {
    suspend fun findByName(name: String): ApplicationEntity?
    suspend fun findByIsPublicIsTrue(): Flow<ApplicationEntity>
    suspend fun findByOwnerId(ownerId: String): Flow<ApplicationEntity>
    suspend fun findByOwnerIdAndClientId(ownerId: String, clientId: String): ApplicationEntity?
    suspend fun findByClientId(clientId: String): ApplicationEntity?
    suspend fun findByClientIdAndClientSecret(clientId: String, clientSecret: String): ApplicationEntity?
}