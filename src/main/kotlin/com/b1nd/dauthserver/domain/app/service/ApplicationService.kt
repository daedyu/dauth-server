package com.b1nd.dauthserver.domain.app.service

import org.springframework.stereotype.Service
import com.b1nd.dauthserver.domain.app.entity.ApplicationEntity
import com.b1nd.dauthserver.domain.app.entity.ApplicationFrameworkEntity
import com.b1nd.dauthserver.domain.app.exception.ApplicationKeyNotMatchException
import com.b1nd.dauthserver.domain.app.repository.ApplicationFrameworkRepository
import com.b1nd.dauthserver.domain.app.repository.ApplicationRepository
import com.b1nd.dauthserver.domain.framework.entity.FrameworkEntity
import kotlinx.coroutines.flow.Flow
import reactor.core.publisher.Flux

@Service
class ApplicationService(
    private val applicationRepository: ApplicationRepository,
    private val applicationFrameworkRepository: ApplicationFrameworkRepository
) {
    suspend fun save(applicationEntity: ApplicationEntity): ApplicationEntity =
        applicationRepository.save(applicationEntity)

    suspend fun saveFrameworks(applicationFrameworkEntities: List<ApplicationFrameworkEntity>) {
        applicationFrameworkEntities
            .map { applicationFrameworkRepository.save(it) }
    }

    suspend fun delete(id: Long) =
        applicationRepository.findById(id)
            ?.let { applicationRepository.delete(it) }

    suspend fun getById(id: Long) =
        applicationRepository.findById(id)

    suspend fun findByClientIdAndSecret(clientId: String, clientSecret: String): ApplicationEntity =
        applicationRepository.findByClientIdAndClientSecret(clientId, clientSecret)?: throw ApplicationKeyNotMatchException()

    suspend fun getAll(): Flow<ApplicationEntity> =
        applicationRepository.findByPublicIsTrue();

    suspend fun getByUserId(ownerId: String): Flow<ApplicationEntity> =
        applicationRepository.findByOwnerId(ownerId)
}