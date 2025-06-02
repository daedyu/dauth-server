package com.b1nd.dauthserver.application.app.data.response

import com.b1nd.dauthserver.domain.app.entity.ApplicationEntity
import com.b1nd.dauthserver.domain.app.entity.data.ApplicationWithFrameworks
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class ApplicationResponse(
    val name: String,
    val url: String,
    val redirectUrl: String?,
    val clientId: String?,
    val clientSecret: String?,
    val frameworks: List<String>
) {
    companion object {
        fun fromEntity(application: ApplicationWithFrameworks, includeSecret: Boolean): ApplicationResponse =
            ApplicationResponse(
                name = application.application.name,
                url = application.application.name,
                frameworks = application.frameworks.map { it.name },
                redirectUrl = if (includeSecret) application.application.redirectUrl else null,
                clientId = if (includeSecret) application.application.clientId else null,
                clientSecret = if (includeSecret) application.application.clientSecret else null
            )

        fun of(applications: List<ApplicationWithFrameworks>): List<ApplicationResponse> =
           applications.map { fromEntity(it, false) }
    }
}
