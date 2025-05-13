package com.b1nd.dauthserver.application.token

import com.b1nd.dauthserver.application.support.response.ResponseData
import com.b1nd.dauthserver.application.token.data.TokenRefreshRequest
import com.b1nd.dauthserver.application.token.data.TokenRefreshResponse
import com.b1nd.dauthserver.application.token.data.TokenRequest
import com.b1nd.dauthserver.application.token.data.TokenResponse
import com.b1nd.dauthserver.domain.app.service.ApplicationService
import com.b1nd.dauthserver.domain.user.exception.UserNotFoundException
import com.b1nd.dauthserver.domain.user.service.UserService
import com.b1nd.dauthserver.infrastructure.client.dodam.DodamClient
import com.b1nd.dauthserver.infrastructure.client.token.TokenClient
import com.b1nd.dauthserver.infrastructure.database.redis.service.RedisService
import com.b1nd.dauthserver.infrastructure.security.token.core.TokenProvider
import com.b1nd.dauthserver.infrastructure.security.token.properties.TokenProperties
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(rollbackFor = [Exception::class])
class TokenUseCase(
    private val tokenClient: TokenClient,
    private val tokenProvider: TokenProvider,
    private val userService: UserService,
    private val redisService: RedisService,
    private val applicationService: ApplicationService
) {
    suspend fun issueToken(request: TokenRequest): ResponseData<TokenResponse> {
        val userId = redisService.get(request.code.toString())
        val user = userService.getById(userId)?: throw UserNotFoundException()
        val application = applicationService.findByClientIdAndSecret(user.client, request.clientSecret)
        val tokens = tokenClient.issueTokens(user.dodamId, user.role.accessLevel)
        val idToken = tokenProvider.generateIdToken(application.clientId, user.role, application.url, user.dodamId,application.clientSecret)
        redisService.delete(request.code.toString())
        return ResponseData.ok("토큰 발급 성공", TokenResponse(tokens.access, tokens.refresh, idToken))
    }

    suspend fun reissueToken(request: TokenRefreshRequest): ResponseData<TokenRefreshResponse> =
        ResponseData.ok("토큰 재발급 성공", TokenRefreshResponse(tokenClient.reissueAccess(request.refresh)))
}