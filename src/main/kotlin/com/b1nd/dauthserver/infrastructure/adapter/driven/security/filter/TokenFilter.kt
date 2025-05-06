package com.b1nd.dauthserver.infrastructure.adapter.driven.security.filter

import com.b1nd.dauthserver.infrastructure.adapter.driven.security.exception.EmptyTokenException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class TokenFilter: OncePerRequestFilter() {
    companion object {
        private const val TOKEN_SECURE_TYPE = "Bearer "
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (!request.getHeader(HttpHeaders.AUTHORIZATION).isNullOrEmpty()) {
            val token: String = request.getHeader(HttpHeaders.AUTHORIZATION)
            if (!token.startsWith(TOKEN_SECURE_TYPE)) throw EmptyTokenException()
//            tokenValidator.validateAll(token.removePrefix(TOKEN_SECURE_TYPE), "ACCESS")
            setAuthentication(token.removePrefix(TOKEN_SECURE_TYPE))
        }
        filterChain.doFilter(request, response)
    }

    private fun setAuthentication(token: String) {
        SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(token, token)
    }
}