package com.b1nd.dauthserver.infrastructure.security.support

import com.b1nd.dauthserver.domain.user.entity.UserEntity
import com.b1nd.dauthserver.domain.user.entity.UserPrincipal
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.security.core.context.ReactiveSecurityContextHolder

object UserAuthenticationHolder {
    suspend fun current(): UserEntity {
        return (ReactiveSecurityContextHolder.getContext()
            .awaitSingle()
            .authentication
            .principal as UserPrincipal).user
    }
}