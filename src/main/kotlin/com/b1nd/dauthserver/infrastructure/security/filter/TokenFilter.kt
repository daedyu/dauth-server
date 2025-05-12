package com.b1nd.dauthserver.infrastructure.security.filter

import com.b1nd.dauthserver.domain.user.entity.UserEntity
import com.b1nd.dauthserver.domain.user.entity.UserPrincipal
import com.b1nd.dauthserver.domain.user.exception.UserNotFoundException
import com.b1nd.dauthserver.domain.user.service.UserService
import com.b1nd.dauthserver.infrastructure.client.token.TokenClient
import io.jsonwebtoken.lang.Strings
import kotlinx.coroutines.reactor.mono
import org.springframework.http.HttpHeaders
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono
import kotlin.text.startsWith
import kotlin.text.substring

@Component
class TokenFilter(
    private val tokenClient: TokenClient,
    private val userService: UserService
): WebFilter {
    companion object {
        private const val PREFIX = "Bearer "
    }

    override fun filter(
        exchange: ServerWebExchange,
        chain: WebFilterChain
    ): Mono<Void> {
            val token = extractToken(exchange.request)
        return if (token.isNotEmpty()) {
            mono {
                val authentication = createAuthentication(token)
                ReactiveSecurityContextHolder.withAuthentication(authentication)
            }.flatMap { context ->
                chain.filter(exchange)
                    .contextWrite(context)
            }
        } else {
            chain.filter(exchange)
        }
    }

    private fun extractToken(request: ServerHttpRequest): String {
        val header = request.headers.getFirst(HttpHeaders.AUTHORIZATION)
        return if (header != null && header.startsWith(PREFIX)) {
            header.substring(PREFIX.length)
        } else {
            Strings.EMPTY
        }
    }

    private suspend fun createAuthentication(token: String): UsernamePasswordAuthenticationToken {
        val principal = getUserPrincipal(token)
        return UsernamePasswordAuthenticationToken(principal, null, principal.authorities)
    }

    private suspend fun getUserPrincipal(token: String): UserPrincipal {
        val token = tokenClient.validateToken(token)
        val user: UserEntity = userService.getByDodamIdAndClient(token.memberId, "key")?: throw UserNotFoundException()
        return UserPrincipal(user)
    }
}
