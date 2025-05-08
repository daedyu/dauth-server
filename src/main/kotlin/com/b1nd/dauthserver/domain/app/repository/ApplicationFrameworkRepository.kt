package com.b1nd.dauthserver.domain.app.repository

import com.b1nd.dauthserver.domain.app.entity.ApplicationFrameworkEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface ApplicationFrameworkRepository: CoroutineCrudRepository<ApplicationFrameworkEntity, Long> {
}