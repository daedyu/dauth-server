package com.b1nd.dauthserver.domain.app.service

import org.springframework.stereotype.Service
import com.b1nd.dauthserver.domain.app.entity.ApplicationEntity
import com.b1nd.dauthserver.domain.app.repository.ApplicationRepository
import kotlinx.coroutines.flow.Flow

@Service
class ApplicationService(
    private val repository: ApplicationRepository
) {
    suspend fun save(applicationEntity: ApplicationEntity): ApplicationEntity =
        repository.save(applicationEntity)

    suspend fun delete(id: Long) =
        repository.findById(id)
            ?.let { repository.delete(it) }

    suspend fun getById(id: Long) =
        repository.findById(id)

    suspend fun getAll(): Flow<ApplicationEntity> =
        repository.findByPublicIsTrue();

    suspend fun getByUserId(ownerId: String): Flow<ApplicationEntity> =
        repository.findByOwnerId(ownerId)
}