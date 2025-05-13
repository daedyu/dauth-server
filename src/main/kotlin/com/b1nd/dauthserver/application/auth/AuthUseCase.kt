package com.b1nd.dauthserver.application.auth

import com.b1nd.dauthserver.application.auth.data.IdLoginRequest
import com.b1nd.dauthserver.application.auth.data.LoginResponse
import com.b1nd.dauthserver.application.support.response.ResponseData
import com.b1nd.dauthserver.domain.user.entity.UserEntity
import com.b1nd.dauthserver.domain.user.service.UserService
import com.b1nd.dauthserver.infrastructure.client.dodam.DodamClient
import com.b1nd.dauthserver.infrastructure.database.redis.service.RedisService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Component
@Transactional(rollbackFor = [Exception::class])
class AuthUseCase(
    private val dodamClient: DodamClient,
    private val userService: UserService,
    private val redisService: RedisService
) {
    suspend fun idLogin(request: IdLoginRequest): ResponseData<LoginResponse> {
        val loginInfo = dodamClient.dodamLogin(request.id, request.password)
        val user: UserEntity = userService.getByDodamIdAndClient(loginInfo.member.id, request.clientId)
            ?.updateInfo(request.scopes)
            ?: request.toEntity(loginInfo.refreshToken, loginInfo.member.role)
        val code = redisService.save(UUID.randomUUID().toString(), userService.save(user).id!!)
        return ResponseData.ok("id로 로그인 성공", LoginResponse.of(code, request.redirectUrl))
    }
}