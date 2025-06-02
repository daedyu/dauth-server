package com.b1nd.dauthserver.domain.app.service

import com.b1nd.dauthserver.domain.app.entity.ApplicationEntity
import com.b1nd.dauthserver.domain.app.entity.ApplicationFrameworkEntity
import com.b1nd.dauthserver.domain.app.entity.data.ApplicationWithFrameworks
import com.b1nd.dauthserver.domain.app.exception.ApplicationKeyNotMatchException
import com.b1nd.dauthserver.domain.app.exception.ApplicationNameAlreadyExistException
import com.b1nd.dauthserver.domain.app.repository.ApplicationFrameworkRepository
import com.b1nd.dauthserver.domain.app.repository.ApplicationQueryRepository
import com.b1nd.dauthserver.domain.app.repository.ApplicationRepository
import com.b1nd.dauthserver.domain.user.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service

@Service
class ApplicationService(
    private val userRepository: UserRepository,
    private val applicationRepository: ApplicationRepository,
    private val applicationFrameworkRepository: ApplicationFrameworkRepository,
    private val applicationQueryRepository: ApplicationQueryRepository
) {
    suspend fun save(applicationEntity: ApplicationEntity): ApplicationEntity =
        applicationRepository.save(applicationEntity)

    suspend fun saveFrameworks(applicationFrameworkEntities: List<ApplicationFrameworkEntity>) {
        applicationFrameworkEntities
            .forEach { applicationFrameworkRepository.save(it) }
    }

    suspend fun validateByName(name: String) =
        applicationRepository.findByName(name)?.let { throw ApplicationNameAlreadyExistException() }

    suspend fun delete(id: Long) =
        applicationRepository.findById(id)
            ?.let { applicationRepository.delete(it) }

    suspend fun getById(id: Long) =
        applicationRepository.findById(id)

    suspend fun countUser(applications: List<ApplicationEntity>) =
        userRepository.countByClientIn(applications.map { it.clientId }.toList())

    suspend fun findByClientIdAndSecret(clientId: String, clientSecret: String): ApplicationEntity =
        applicationRepository.findByClientIdAndClientSecret(clientId, clientSecret)?: throw ApplicationKeyNotMatchException()

    suspend fun getAll(): List<ApplicationWithFrameworks> =
        applicationQueryRepository.findAllApplicationsWithFrameworks()

    suspend fun getByUserId(ownerId: String): List<ApplicationEntity> =
        applicationRepository.findByOwnerId(ownerId).toList()
}