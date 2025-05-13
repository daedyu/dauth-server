package com.b1nd.dauthserver.infrastructure.security.token.core

import com.b1nd.dauthserver.domain.user.enumeration.RoleType
import com.b1nd.dauthserver.infrastructure.security.token.properties.TokenProperties
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import java.lang.System.currentTimeMillis
import java.nio.charset.StandardCharsets
import java.util.Date
import javax.crypto.spec.SecretKeySpec

@Component
class TokenProvider(
    private val properties: TokenProperties,
) {
    fun generateIdToken(clientId: String, role: RoleType, clientUrl: String, dodamId: String, key: String): String =
        Jwts.builder()
            .claim("iss", clientUrl)
            .claim("aud", clientId)
            .claim("sub", dodamId)
            .claim("role", role.name)
            .claim("exp", Date(currentTimeMillis()+ properties.expire))
            .issuedAt(Date(currentTimeMillis()))
            .expiration(Date(currentTimeMillis() + properties.expire))
            .signWith(secretKey(key))
            .compact()

    private fun secretKey(key: String) =
        SecretKeySpec(key.toByteArray(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().algorithm)
}
