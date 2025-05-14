package com.b1nd.dauthserver.presentation.auth

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.b1nd.dauthserver.application.support.response.ResponseData
import com.b1nd.dauthserver.application.auth.AuthUseCase
import com.b1nd.dauthserver.application.auth.data.CheckQrRequest
import com.b1nd.dauthserver.application.auth.data.CreateQrRequest
import com.b1nd.dauthserver.application.auth.data.IdLoginRequest
import com.b1nd.dauthserver.application.auth.data.LoginResponse
import com.b1nd.dauthserver.application.auth.data.QrLoginRequest
import com.b1nd.dauthserver.application.auth.data.QrLoginResponse
import com.b1nd.dauthserver.application.support.response.Response
import org.springframework.web.bind.annotation.GetMapping

@RestController
@RequestMapping("/auth")
class AuthController(
    private val useCase: AuthUseCase
) {
    @PostMapping("/id-login")
    suspend fun login(@Valid @RequestBody request: IdLoginRequest): ResponseData<LoginResponse> =
        useCase.idLogin(request)

    @GetMapping("/qr")
    suspend fun createQr(@Valid @RequestBody request: CreateQrRequest): ResponseData<QrLoginResponse> =
        useCase.createQr(request)

    @PostMapping("/qr-login")
    suspend fun qrLogin(@Valid @RequestBody request: QrLoginRequest): Response =
        useCase.qrLogin(request)

    @GetMapping("/qr/check")
    suspend fun checkQrLogin(@Valid @RequestBody request: CheckQrRequest): ResponseData<LoginResponse> =
        useCase.checkQrLogin(request)
}