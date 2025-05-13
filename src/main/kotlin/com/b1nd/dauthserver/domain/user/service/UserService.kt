package com.b1nd.dauthserver.domain.user.service

import com.b1nd.dauthserver.domain.user.entity.UserEntity
import com.b1nd.dauthserver.domain.user.enumeration.ScopeType
import com.b1nd.dauthserver.domain.user.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(
    private val repository: UserRepository
) {
    suspend fun getById(id: Long): UserEntity? =
        repository.findById(id)

    suspend fun getByDodamIdAndClient(dodamId: String, clientId: String): UserEntity? =
        repository.findByDodamIdAndClient(dodamId, clientId)

    suspend fun save(userEntity: UserEntity): UserEntity =
        repository.save(userEntity)
}
