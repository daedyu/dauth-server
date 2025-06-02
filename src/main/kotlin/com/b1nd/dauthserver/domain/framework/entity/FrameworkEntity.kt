package com.b1nd.dauthserver.domain.framework.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import com.b1nd.dauthserver.domain.framework.enumeration.FrameworkType

@Table("frameworks")
data class FrameworkEntity(
    @Id
    val id: Long? = null,
    val name: String,
    val type: FrameworkType,
    val color: String
)