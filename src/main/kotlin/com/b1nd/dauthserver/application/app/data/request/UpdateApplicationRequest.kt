package com.b1nd.dauthserver.application.app.data.request

import jakarta.annotation.Nullable
import jakarta.validation.constraints.NotBlank

data class UpdateApplicationRequest(
    @NotBlank
    val clientId: String,
    @Nullable
    val name: String?,
    @Nullable
    val url: String?,
    @Nullable
    val redirectUrl: String?,
    @Nullable
    val isPublic: Boolean?,
    @Nullable
    val frameworks: List<String>
)