package com.b1nd.dauthserver.presentation.app

import com.b1nd.dauthserver.application.app.ApplicationUseCase
import com.b1nd.dauthserver.application.app.data.request.CreateApplicationRequest
import com.b1nd.dauthserver.application.app.data.request.UpdateApplicationRequest
import com.b1nd.dauthserver.application.app.data.request.UpdateOwnerRequest
import com.b1nd.dauthserver.application.app.data.response.ApplicationResponse
import com.b1nd.dauthserver.application.app.data.response.MyApplicationResponse
import com.b1nd.dauthserver.application.support.response.Response
import com.b1nd.dauthserver.application.support.response.ResponseData
import jakarta.validation.Valid
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/app")
class ApplicationController(
    private val useCase: ApplicationUseCase
) {
    @PostMapping
    suspend fun create(@Valid @RequestBody request: CreateApplicationRequest): Response =
        useCase.create(request)

    @PatchMapping("/owner")
    suspend fun updateOwner(@Valid @RequestBody request: UpdateOwnerRequest): Response =
        useCase.updateOwner(request)

    @PatchMapping
    suspend fun updateApplication(@Valid @RequestBody request: UpdateApplicationRequest): Response =
        useCase.updateInfo(request)

    @GetMapping("/my")
    suspend fun getMy(): ResponseData<MyApplicationResponse> =
        useCase.getMy()

    @GetMapping
    suspend fun getAll(): ResponseData<List<ApplicationResponse>> =
        useCase.getAll()
}