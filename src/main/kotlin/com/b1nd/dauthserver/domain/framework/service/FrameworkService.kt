package com.b1nd.dauthserver.domain.framework.service

import com.b1nd.dauthserver.domain.framework.entity.FrameworkEntity
import com.b1nd.dauthserver.domain.framework.exception.FrameworkNotFoundException
import com.b1nd.dauthserver.domain.framework.repository.FrameworkRepository
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service

@Service
class FrameworkService(
    private val repository: FrameworkRepository
) {
    suspend fun getAll() =
        repository.findAll()

    suspend fun getByNameIn(names: List<String>): List<FrameworkEntity> =
        repository.findByNameIn(names).toList()

    suspend fun validateByIdIn(ids: List<Long>) {
        if (repository.findByIdIn(ids).toList().isEmpty()) throw FrameworkNotFoundException()
    }
}
