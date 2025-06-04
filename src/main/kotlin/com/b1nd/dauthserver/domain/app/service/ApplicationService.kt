package com.b1nd.dauthserver.domain.app.service

import com.b1nd.dauthserver.domain.app.entity.ApplicationEntity
import com.b1nd.dauthserver.domain.app.entity.ApplicationFrameworkEntity
import com.b1nd.dauthserver.domain.app.entity.data.ApplicationWithFrameworks
import com.b1nd.dauthserver.domain.app.exception.ApplicationKeyNotMatchException
import com.b1nd.dauthserver.domain.app.exception.ApplicationNameAlreadyExistException
import com.b1nd.dauthserver.domain.app.exception.ApplicationNotFoundException
import com.b1nd.dauthserver.domain.app.repository.ApplicationFrameworkRepository
import com.b1nd.dauthserver.domain.app.repository.ApplicationQueryRepository
import com.b1nd.dauthserver.domain.app.repository.ApplicationRepository
import com.b1nd.dauthserver.domain.framework.entity.FrameworkEntity
import com.b1nd.dauthserver.domain.user.repository.UserRepository
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

    suspend fun updateInfo(ownerId: String, clientId: String, name: String?, url: String?, redirectUrl: String?, isPublic: Boolean?, frameworks: List<FrameworkEntity>) {
        val application = applicationRepository.findByOwnerIdAndClientId(ownerId, clientId)
            ?: throw ApplicationNotFoundException()
        application.updateInfo(name, url, redirectUrl, isPublic)
        applicationRepository.save(application)
        saveFrameworks(frameworks.map {
            ApplicationFrameworkEntity(applicationId = application.id!!, frameworkId = it.id!!)
        })
    }

    suspend fun updateOwner(ownerId: String, clientId: String, dodamId: String) {
        val application = applicationRepository.findByOwnerIdAndClientId(ownerId, clientId)
            ?: throw ApplicationNotFoundException()
        application.updateOwner(dodamId)
        applicationRepository.save(application)
    }

    suspend fun getById(id: Long) =
        applicationRepository.findById(id)

    suspend fun countUser(applications: List<ApplicationEntity>) =
        userRepository.countByClientIn(applications.map { it.clientId }.toList())

    suspend fun findByClientIdAndSecret(clientId: String, clientSecret: String): ApplicationEntity =
        applicationRepository.findByClientIdAndClientSecret(clientId, clientSecret)?: throw ApplicationKeyNotMatchException()

    suspend fun getAll(): List<ApplicationWithFrameworks> =
        applicationQueryRepository.findAllApplicationsWithFrameworks()

    suspend fun getByUserId(ownerId: String): List<ApplicationWithFrameworks> =
        applicationQueryRepository.findApplicationsByOwnerId(ownerId)
}