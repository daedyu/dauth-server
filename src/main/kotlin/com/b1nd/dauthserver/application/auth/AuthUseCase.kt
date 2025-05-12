package com.b1nd.dauthserver.application.auth

import com.b1nd.dauthserver.application.auth.data.IdLoginRequest
import com.b1nd.dauthserver.application.auth.data.LoginResponse
import com.b1nd.dauthserver.application.support.response.ResponseData
import com.b1nd.dauthserver.domain.user.entity.UserEntity
import com.b1nd.dauthserver.domain.user.service.UserService
import com.b1nd.dauthserver.infrastructure.client.dodam.DodamClient
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(rollbackFor = [Exception::class])
class AuthUseCase(
    private val dodamClient: DodamClient,
    private val userService: UserService,
) {
    suspend fun idLogin(request: IdLoginRequest): ResponseData<LoginResponse> {
        val loginInfo = dodamClient.dodamLogin(request.id, request.password)
        val user: UserEntity = userService.getByDodamIdAndClient(loginInfo.member.id, request.clientId)
            ?.updateInfo(loginInfo.refreshToken, request.scopes)
            ?: request.toEntity(loginInfo.refreshToken, loginInfo.member.role)
        return ResponseData.ok("id로 로그인 성공", LoginResponse.of(
            userService.save(user).id.toString(), loginInfo.refreshToken)
        )
    }
}