package com.b1nd.dauthserver.domain.auth.repository

import com.b1nd.dauthserver.domain.auth.entity.AuthenticationWordEntity
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface AuthenticationWordRepository: CoroutineCrudRepository<AuthenticationWordEntity, Long> {
    @Query("SELECT * FROM authentication_words ORDER BY RAND() LIMIT 1")
    suspend fun findRandomOne(): AuthenticationWordEntity?
}