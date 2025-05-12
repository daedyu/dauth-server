package com.b1nd.dauthserver.presentation.app

import com.b1nd.dauthserver.application.app.ApplicationUseCase
import com.b1nd.dauthserver.application.app.data.CreateApplicationRequest
import com.b1nd.dauthserver.application.support.response.Response
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/app")
class ApplicationController(
    private val useCase: ApplicationUseCase
) {
    @PostMapping
    suspend fun create(@Valid @RequestBody request: CreateApplicationRequest): Response =
        useCase.create(request)
}