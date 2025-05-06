package com.b1nd.dauthserver.application.client.data

import com.b1nd.dauthserver.domain.client.entity.ClientEntity

data class Client(
    val name: String,
    val frontFrameWork: Int,
    val backendFrameWork: Int,
    val url: String,
    val redirectUrl: String,
) {
    fun toEntity(dodamId: String): ClientEntity =
        ClientEntity(
            dodamId = dodamId,
            clientName = name,
            clientUrl = url,
            redirectUrl = redirectUrl
        )
}