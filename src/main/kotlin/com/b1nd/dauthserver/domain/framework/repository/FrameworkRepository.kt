package com.b1nd.dauthserver.domain.framework.repository

import com.b1nd.dauthserver.domain.framework.entity.FrameworkEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface FrameworkRepository: CoroutineCrudRepository<FrameworkEntity, Long> {
}