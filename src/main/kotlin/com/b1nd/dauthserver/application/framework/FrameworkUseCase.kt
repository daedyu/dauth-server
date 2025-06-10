package com.b1nd.dauthserver.application.framework

import com.b1nd.dauthserver.application.framework.data.FrameworkResponse
import com.b1nd.dauthserver.application.support.response.ResponseData
import com.b1nd.dauthserver.domain.framework.service.FrameworkService
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Component

@Component
class FrameworkUseCase(
    private val frameworkService: FrameworkService
) {
    suspend fun getFrameworks(): ResponseData<List<FrameworkResponse>> =
        ResponseData.ok("프레임워크 조회 성공", FrameworkResponse.of(frameworkService.getAll().toList()))

}