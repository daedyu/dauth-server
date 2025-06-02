package com.b1nd.dauthserver.application.app

import com.b1nd.dauthserver.application.app.data.request.CreateApplicationRequest
import com.b1nd.dauthserver.application.app.data.response.ApplicationResponse
import com.b1nd.dauthserver.application.app.data.response.MyApplicationResponse
import com.b1nd.dauthserver.application.support.response.Response
import com.b1nd.dauthserver.domain.app.service.ApplicationService
import com.b1nd.dauthserver.domain.framework.service.FrameworkService
import com.b1nd.dauthserver.infrastructure.security.support.UserAuthenticationHolder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(rollbackFor = [Exception::class])
class ApplicationUseCase(
    private val applicationService: ApplicationService,
    private val frameworkService: FrameworkService
) {
    suspend fun create(request: CreateApplicationRequest): Response {
        val user = UserAuthenticationHolder.current()
        validateOnCreate(request)
        val application = applicationService.save(request.toEntity(user.dodamId))
        applicationService.saveFrameworks(request.toFrameWorks(application.id!!))
        return Response.ok("앱 등록 성공")
    }

    private suspend fun validateOnCreate(request: CreateApplicationRequest) {
        applicationService.validateByName(request.name)
        frameworkService.validateByIdIn(request.frameworks)
    }

    suspend fun getAll(): List<ApplicationResponse> =
        ApplicationResponse.of(applicationService.getAll()).toList()

    suspend fun getMy(): MyApplicationResponse {
        val user = UserAuthenticationHolder.current()
        val applications = applicationService.getByUserId(user.dodamId)
        return MyApplicationResponse(
            applicationService.countUser(applications.map { it.application }),
            ApplicationResponse.ofWithSecret(applications)
        )
    }
}