package com.b1nd.dauthserver.domain.framework.repository

import kotlinx.coroutines.flow.Flow
import com.b1nd.dauthserver.domain.framework.entity.FrameworkEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface FrameworkRepository: CoroutineCrudRepository<FrameworkEntity, Long> {
    suspend fun findByIdIn(ids: List<Long>): Flow<FrameworkEntity>
}