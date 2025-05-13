package com.b1nd.dauthserver.domain.app.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("application_frameworks")
data class ApplicationFrameworkEntity(
    @field:Id
    val id: Long? = null,
    @field:Column("fk_application_id")
    val applicationId: Long,
    @field:Column("fk_framework_id")
    val frameworkId: Long
)
