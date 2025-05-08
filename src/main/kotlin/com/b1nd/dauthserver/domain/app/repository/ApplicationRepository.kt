package com.b1nd.dauthserver.domain.app.repository

import kotlinx.coroutines.flow.Flow
import com.b1nd.dauthserver.domain.app.entity.ApplicationEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface ApplicationRepository: CoroutineCrudRepository<ApplicationEntity, Long> {
    fun findByPublicIsTrue(): Flow<ApplicationEntity>
    fun findByDodamId(dodamId: String): Flow<ApplicationEntity>
}