package com.b1nd.dauthserver.application.framework.data

import com.b1nd.dauthserver.domain.framework.entity.FrameworkEntity
import com.b1nd.dauthserver.domain.framework.enumeration.FrameworkType

data class FrameworkResponse(
    val id: Long,
    val name: String,
    val color: String,
    val type: FrameworkType
) {
    companion object {
        fun of(frameworkEntities: List<FrameworkEntity>): List<FrameworkResponse> =
            frameworkEntities.map { of(it) }

        fun of(frameworkEntity: FrameworkEntity): FrameworkResponse =
            FrameworkResponse(
                id = frameworkEntity.id!!,
                name = frameworkEntity.name,
                color = frameworkEntity.color,
                type = frameworkEntity.type
            )
    }
}
