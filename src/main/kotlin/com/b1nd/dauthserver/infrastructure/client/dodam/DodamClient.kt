package com.b1nd.dauthserver.infrastructure.client.dodam

import com.b1nd.dauthserver.infrastructure.client.dodam.data.DodamLoginRequest
import com.b1nd.dauthserver.infrastructure.client.dodam.data.DodamLoginResponse
import com.b1nd.dauthserver.infrastructure.client.dodam.exception.DodamClientException
import com.b1nd.dauthserver.infrastructure.client.dodam.properties.DodamProperties
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
class DodamClient(
    private val webClient: WebClient,
    private val properties: DodamProperties
) {
    suspend fun dodamLogin(id: String, password: String): DodamLoginResponse {
        return webClient.post()
            .uri(properties.endpoint+"/auth/login")
            .body(DodamLoginRequest(id, password), DodamLoginRequest::class.java)
            .retrieve()
            .onStatus(HttpStatusCode::isError) { response: ClientResponse ->
                throw DodamClientException(response.statusCode().value())
            }
            .awaitBody<DodamLoginResponse>()
    }
}