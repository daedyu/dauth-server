package com.b1nd.dauthserver.presentation.auth

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.b1nd.dauthserver.application.support.response.ResponseData
import com.b1nd.dauthserver.application.auth.AuthUseCase
import com.b1nd.dauthserver.application.auth.data.IdLoginRequest
import com.b1nd.dauthserver.application.auth.data.LoginResponse

@RestController
@RequestMapping("/auth")
class AuthController(
    private val useCase: AuthUseCase
) {
    @PostMapping("/login")
    suspend fun login(@Valid @RequestBody request: IdLoginRequest): ResponseData<LoginResponse> =
        useCase.idLogin(request)

}