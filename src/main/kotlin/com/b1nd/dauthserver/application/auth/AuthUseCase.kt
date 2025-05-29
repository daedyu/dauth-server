package com.b1nd.dauthserver.application.auth

import com.b1nd.dauthserver.application.auth.data.CheckQrRequest
import com.b1nd.dauthserver.application.auth.data.CreateQrRequest
import com.b1nd.dauthserver.application.auth.data.IdLoginRequest
import com.b1nd.dauthserver.application.auth.data.LoginResponse
import com.b1nd.dauthserver.application.auth.data.QrLoginRequest
import com.b1nd.dauthserver.application.auth.data.QrLoginResponse
import com.b1nd.dauthserver.application.support.response.Response
import com.b1nd.dauthserver.application.support.response.ResponseData
import com.b1nd.dauthserver.domain.auth.service.AuthenticationWordService
import com.b1nd.dauthserver.domain.user.entity.UserEntity
import com.b1nd.dauthserver.domain.user.enumeration.ScopeType
import com.b1nd.dauthserver.domain.user.exception.CodeNotAppliedException
import com.b1nd.dauthserver.domain.user.service.UserService
import com.b1nd.dauthserver.infrastructure.client.dodam.DodamClient
import com.b1nd.dauthserver.infrastructure.database.redis.enumeration.RedisKeyType
import com.b1nd.dauthserver.infrastructure.database.redis.service.RedisService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Component
@Transactional(rollbackFor = [Exception::class])
class AuthUseCase(
    private val dodamClient: DodamClient,
    private val userService: UserService,
    private val authService: AuthenticationWordService,
    private val redisService: RedisService
) {
    suspend fun idLogin(request: IdLoginRequest): ResponseData<LoginResponse> {
        val loginInfo = dodamClient.dodamLogin(request.id, request.password)
        val newUser = request.toEntity(loginInfo.refreshToken, loginInfo.member.role)
        val user = saveOrUpdateUser(loginInfo.member.id, request.clientId, loginInfo.refreshToken, request.scopes, newUser)
        return ResponseData.ok("id로 로그인 성공", LoginResponse.of(createCode(user.id!!), request.redirectUrl))
    }

    suspend fun createQr(request: CreateQrRequest): ResponseData<QrLoginResponse> {
        val code = UUID.randomUUID().toString()
        val words = authService.getRandomCode()
        val word = authService.getWord(words)
        redisService.save(RedisKeyType.QR_SCOPES, code, request.scopes.joinToString(" ") { it.value })
        redisService.save(RedisKeyType.QR_LOGIN_CHECKED, code + word, "")
        return ResponseData.created("qr용 코드 생성 성공", QrLoginResponse(code, words, word))
    }

    suspend fun qrLogin(request: QrLoginRequest): Response {
        val scopes = redisService.get(RedisKeyType.QR_SCOPES, request.code).split(" ").map { ScopeType.fromValue(it) }
        val loginInfo = dodamClient.dodamMy(request.access)
        val newUser = UserEntity(dodamId = loginInfo.id, client = request.clientId, scopes = scopes, refreshToken = request.refresh, role = loginInfo.role)
        val user = saveOrUpdateUser(loginInfo.id, request.clientId, request.refresh, scopes, newUser)
        redisService.update(RedisKeyType.QR_LOGIN_CHECKED, request.code + request.word, user.id!!.toString())
        redisService.delete(RedisKeyType.QR_SCOPES, request.code)
        return Response.ok("qr 로그인 성공")
    }

    suspend fun checkQrLogin(request: CheckQrRequest): ResponseData<LoginResponse> {
        val userId = redisService.get(RedisKeyType.QR_LOGIN_CHECKED, request.code + request.word)
        if (userId.isBlank()) throw CodeNotAppliedException()
        val user = userService.getById(userId.toLong())?: throw CodeNotAppliedException()
        redisService.delete(RedisKeyType.QR_LOGIN_CHECKED, request.code + request.word)
        return ResponseData.ok("qr 로그인 조회 성공", LoginResponse.of(createCode(user.id!!), request.redirectUrl))
    }

    private suspend fun createCode(userId: Long): String =
        redisService.save(RedisKeyType.LOGIN_TOKEN, UUID.randomUUID().toString(), userId.toString())

    private suspend fun saveOrUpdateUser(dodamId: String, clientId: String, refresh: String, scopes: List<ScopeType>, newUser: UserEntity) =
        userService.save(userService.getByDodamIdAndClient(dodamId, clientId)
            ?.updateInfo(refresh, scopes)
            ?: newUser)
}