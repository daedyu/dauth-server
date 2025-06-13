package com.b1nd.dauthserver.application.app

import com.b1nd.dauthserver.application.app.data.request.CreateApplicationRequest
import com.b1nd.dauthserver.application.app.data.request.UpdateApplicationRequest
import com.b1nd.dauthserver.application.app.data.request.UpdateOwnerRequest
import com.b1nd.dauthserver.application.app.data.response.ApplicationResponse
import com.b1nd.dauthserver.application.app.data.response.MyApplicationResponse
import com.b1nd.dauthserver.application.support.response.Response
import com.b1nd.dauthserver.application.support.response.ResponseData
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
        return Response.created("앱 등록 성공")
    }

    private suspend fun validateOnCreate(request: CreateApplicationRequest) {
        applicationService.validateByName(request.name)
        frameworkService.validateByIdIn(request.frameworks)
    }

    suspend fun updateInfo(request: UpdateApplicationRequest): Response {
        val user = UserAuthenticationHolder.current()
        val frameworks = frameworkService.getByNameIn(request.frameworks)
        applicationService.updateInfo(user.dodamId, request.clientId, request.name, request.url, request.redirectUrl, request.isPublic, frameworks)
        return Response.ok("어플리케이션 정보 변경 성공")
    }

    suspend fun updateOwner(request: UpdateOwnerRequest): Response {
        val user = UserAuthenticationHolder.current()
        applicationService.updateOwner(user.dodamId, request.clientId, request.newOwnerDodamId)
        return Response.ok("소유자 변경 성공")
    }

    suspend fun getAll(): ResponseData<List<ApplicationResponse>> =
        ResponseData.ok("어플리케이션 조회 성공", ApplicationResponse.of(applicationService.getAll()).toList())

    suspend fun getMy(): ResponseData<MyApplicationResponse> {
        val user = UserAuthenticationHolder.current()
        val applications = applicationService.getByUserId(user.dodamId)
        return ResponseData.ok(
            "내 어플리케이션 조회 성공",
            MyApplicationResponse(
                applicationService.countUser(applications.map { it.application }),
                ApplicationResponse.ofWithSecret(applications)
            )
        )
    }
}