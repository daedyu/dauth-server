package com.b1nd.dauthserver.domain.auth.service

import com.b1nd.dauthserver.domain.auth.exception.AuthenticationWordNotFoundException
import com.b1nd.dauthserver.domain.auth.repository.AuthenticationWordRepository
import org.springframework.stereotype.Service

@Service
class AuthenticationWordService(
    private val repository: AuthenticationWordRepository
) {
    suspend fun getRandomCode(): String =
        repository.findRandomOne()?.word
            ?: throw AuthenticationWordNotFoundException()

    fun getWord(word: String) =
        word.random().toString()
}