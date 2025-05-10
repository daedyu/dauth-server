package com.b1nd.dauthserver.application.user

import com.b1nd.dauthserver.application.user.data.IdLoginRequest
import com.b1nd.dauthserver.application.user.data.LoginResponse
import com.b1nd.dauthserver.domain.user.entity.UserEntity
import com.b1nd.dauthserver.domain.user.service.UserService
import com.b1nd.dauthserver.infrastructure.client.dodam.DodamClient
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(rollbackFor = [Exception::class])
class UserUseCase(
    private val service: UserService,
    private val dodamClient: DodamClient
) {
    suspend fun idLogin(request: IdLoginRequest): LoginResponse {
        val loginInfo = dodamClient.dodamLogin(request.id, request.password)
        val user: UserEntity = service.getByDodamIdAndClient(loginInfo.member.id, request.clientId)
            ?.updateInfo(loginInfo.refreshToken, request.scopes)
            ?: request.toEntity(loginInfo.refreshToken)
        service.save(user)
        return LoginResponse.of(user.id.toString(), loginInfo.refreshToken)
    }
}