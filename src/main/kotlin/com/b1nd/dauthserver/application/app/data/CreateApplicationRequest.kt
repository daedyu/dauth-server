package com.b1nd.dauthserver.application.app.data

import com.b1nd.dauthserver.domain.app.entity.ApplicationEntity
import com.b1nd.dauthserver.domain.app.entity.ApplicationFrameworkEntity
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import java.util.UUID

data class CreateApplicationRequest(
    @field:NotBlank
    val name: String,
    @field:NotBlank
    val url: String,
    @field:NotBlank
    val redirectUrl: String,
    val isPublic: Boolean,
    @field:NotEmpty
    val frameworks: List<Long>
) {
    fun toEntity(dodamId: String) =
        ApplicationEntity(
            name = name,
            url = url,
            redirectUrl = redirectUrl,
            isPublic = isPublic,
            ownerId = dodamId,
            clientId = UUID.randomUUID().toString(),
            clientSecret = UUID.randomUUID().toString()
        )

    fun toFrameWorks(applicationId: Long): List<ApplicationFrameworkEntity> =
        frameworks.map { ApplicationFrameworkEntity(applicationId = applicationId, frameworkId = it) }
}