package com.b1nd.dauthserver.presentation.user

import com.b1nd.dauthserver.application.user.UserUseCase
import com.b1nd.dauthserver.application.user.data.IdLoginRequest
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val useCase: UserUseCase
) {
    @PostMapping("/login")
    suspend fun login(@Valid @RequestBody request: IdLoginRequest) {
        useCase.idLogin(request)
    }
}