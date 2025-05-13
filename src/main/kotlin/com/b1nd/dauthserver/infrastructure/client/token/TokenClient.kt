package com.b1nd.dauthserver.infrastructure.client.token

import com.b1nd.dauthserver.infrastructure.client.token.data.TokenIssueRequest
import com.b1nd.dauthserver.infrastructure.client.token.data.TokenIssueResponse
import com.b1nd.dauthserver.infrastructure.client.token.data.TokenReissueRequest
import com.b1nd.dauthserver.infrastructure.client.token.data.TokenValidateRequest
import com.b1nd.dauthserver.infrastructure.client.token.data.TokenValidateResponse
import com.b1nd.dauthserver.infrastructure.client.token.data.TokensIssueResponse
import com.b1nd.dauthserver.infrastructure.client.token.exception.TokenClientException
import com.b1nd.dauthserver.infrastructure.client.token.properties.TokenClientProperties
import com.b1nd.dauthserver.infrastructure.web.data.BasicApiResponse
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
class TokenClient(
    private val webClient: WebClient,
    private val properties: TokenClientProperties
) {
    suspend fun issueTokens(memberId: String, accessLevel: Int): TokensIssueResponse =
        TokensIssueResponse(
            issue(TokenIssueRequest(memberId, accessLevel, properties.key), properties.access),
            issue(TokenIssueRequest(memberId, accessLevel, properties.key), properties.refresh)
        )

    private suspend fun issue(request: TokenIssueRequest, uri: String): String =
        webClient.post()
            .uri(properties.tokenEndpoint + uri)
            .bodyValue(request)
            .retrieve()
            .onStatus(HttpStatusCode::isError) { response: ClientResponse ->
                throw TokenClientException(response.statusCode().value())
            }
            .awaitBody<BasicApiResponse<TokenIssueResponse>>().data.token

    suspend fun validateToken(token: String): TokenValidateResponse =
        webClient.post()
            .uri(properties.tokenEndpoint + properties.verify)
            .bodyValue(TokenValidateRequest(token, properties.key))
            .retrieve()
            .onStatus(HttpStatusCode::isError) { response: ClientResponse ->
                throw TokenClientException(response.statusCode().value())
            }
            .awaitBody<BasicApiResponse<TokenValidateResponse>>().data

    suspend fun reissueAccess(token: String): String =
        webClient.post()
            .uri(properties.tokenEndpoint + properties.reissue)
            .bodyValue(TokenReissueRequest(token, properties.key))
            .retrieve()
            .onStatus(HttpStatusCode::isError) { response: ClientResponse ->
                throw TokenClientException(response.statusCode().value())
            }
            .awaitBody<BasicApiResponse<TokenIssueResponse>>().data.token
}