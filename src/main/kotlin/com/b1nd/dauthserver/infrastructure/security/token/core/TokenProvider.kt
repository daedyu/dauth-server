package com.b1nd.dauthserver.infrastructure.security.token.core

import com.b1nd.dauthserver.domain.user.enumeration.RoleType
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import java.lang.System.currentTimeMillis
import java.nio.charset.StandardCharsets
import java.util.Date
import javax.crypto.spec.SecretKeySpec

@Component
class TokenProvider {
    fun generateIdToken(clientId: String, role: RoleType, clientUrl: String, dodamId: String, expire: Long, key: String): String =
        Jwts.builder()
            .claim("iss", clientUrl)
            .claim("aud", clientId)
            .claim("sub", dodamId)
            .claim("role", role.name)
            .claim("exp", Date(currentTimeMillis()+ expire))
            .issuedAt(Date(currentTimeMillis()))
            .expiration(Date(currentTimeMillis() + expire))
            .signWith(secretKey(key))
            .compact()

    private fun secretKey(key: String) =
        SecretKeySpec(key.toByteArray(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().algorithm)
}
