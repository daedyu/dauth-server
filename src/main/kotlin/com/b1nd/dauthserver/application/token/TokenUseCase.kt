package com.b1nd.dauthserver.application.token

import com.b1nd.dauthserver.application.support.response.ResponseData
import com.b1nd.dauthserver.application.token.data.TokenRequest
import com.b1nd.dauthserver.application.token.data.TokenResponse
import com.b1nd.dauthserver.domain.app.service.ApplicationService
import com.b1nd.dauthserver.domain.user.exception.UserNotFoundException
import com.b1nd.dauthserver.domain.user.service.UserService
import com.b1nd.dauthserver.infrastructure.client.token.TokenClient
import com.b1nd.dauthserver.infrastructure.security.token.core.TokenProvider
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(rollbackFor = [Exception::class])
class TokenUseCase(
    private val tokenClient: TokenClient,
    private val tokenProvider: TokenProvider,
    private val userService: UserService,
    private val applicationService: ApplicationService,
) {
    suspend fun issueToken(request: TokenRequest): ResponseData<TokenResponse> {
        val user = userService.getById(request.code) ?: throw UserNotFoundException()
        val application = applicationService.findByClientIdAndSecret(user.client, request.clientSecret)
        val tokens = tokenClient.issueTokens(user.dodamId, user.role.accessLevel)
        val idToken = tokenProvider.generateIdToken(
            application.clientId,
            user.role,
            application.url,
            user.dodamId,
            123123,
            application.clientSecret
        )
        return ResponseData.ok("토큰 발급 성공", TokenResponse(tokens.access, tokens.refresh, idToken))
    }
}