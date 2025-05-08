package com.b1nd.dauthserver.domain.user.repository

import com.b1nd.dauthserver.domain.user.entity.UserEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface UserRepository: CoroutineCrudRepository<UserEntity, Long> {
}