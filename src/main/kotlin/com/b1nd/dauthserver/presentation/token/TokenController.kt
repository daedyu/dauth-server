package com.b1nd.dauthserver.presentation.token

import com.b1nd.dauthserver.application.support.response.ResponseData
import com.b1nd.dauthserver.application.token.TokenUseCase
import com.b1nd.dauthserver.application.token.data.TokenRequest
import com.b1nd.dauthserver.application.token.data.TokenResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TokenController(
    private val useCase: TokenUseCase
) {
    @PostMapping("/token")
    suspend fun issueToken(@RequestBody request: TokenRequest): ResponseData<TokenResponse> =
        useCase.issueToken(request)
}