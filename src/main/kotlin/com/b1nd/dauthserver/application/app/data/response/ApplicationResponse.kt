package com.b1nd.dauthserver.application.app.data.response

import com.b1nd.dauthserver.domain.app.entity.ApplicationEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class ApplicationResponse(
    val name: String,
    val url: String,
    val redirectUrl: String?,
    val clientId: String?,
    val clientSecret: String?
) {
    companion object {
        private fun fromEntity(application: ApplicationEntity, includeSecrets: Boolean): ApplicationResponse =
            ApplicationResponse(
                name = application.name,
                url = application.url,
                redirectUrl = if (includeSecrets) application.redirectUrl else null,
                clientId = if (includeSecrets) application.clientId else null,
                clientSecret = if (includeSecrets) application.clientSecret else null
            )

        fun ofWithSecret(applications: Flow<ApplicationEntity>): Flow<ApplicationResponse> =
            applications.map { fromEntity(it, includeSecrets = true) }

        fun of(applications: Flow<ApplicationEntity>): Flow<ApplicationResponse> =
            applications.map { fromEntity(it, includeSecrets = false) }
    }
}
