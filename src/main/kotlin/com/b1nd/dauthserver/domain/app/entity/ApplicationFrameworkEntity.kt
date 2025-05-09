package com.b1nd.dauthserver.domain.app.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("application_frameworks")
data class ApplicationFrameworkEntity(
    @Id
    val id: Long? = null,
    val applicationId: Long,
    val frameworkId: Long
)
