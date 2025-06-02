package com.b1nd.dauthserver.domain.app.entity.data

import com.b1nd.dauthserver.domain.app.entity.ApplicationEntity
import com.b1nd.dauthserver.domain.framework.entity.FrameworkEntity

data class ApplicationWithFrameworks(
    val application: ApplicationEntity,
    val frameworks: List<FrameworkEntity>
)
