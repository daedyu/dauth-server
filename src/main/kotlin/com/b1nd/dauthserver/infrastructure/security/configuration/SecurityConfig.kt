package com.b1nd.dauthserver.infrastructure.security.configuration

import com.b1nd.dauthserver.infrastructure.security.filter.TokenFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsConfigurationSource
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebFluxSecurity
class SecurityConfig(
    private val tokenFilter: TokenFilter
) {
    @Bean
    protected fun filterChain(http: ServerHttpSecurity): SecurityWebFilterChain =
        http
            .httpBasic { it.disable() }
            .formLogin { it.disable() }
            .csrf { it.disable() }
            .cors { corsSpec -> corsSpec.configurationSource(corsConfigurationSource()) }
            .authorizeExchange { it
                .pathMatchers("/").permitAll()
                .pathMatchers("/auth/**").permitAll()
                .anyExchange().permitAll()
            }
            .addFilterAt(tokenFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            .build()

    @Bean
    protected fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration().apply {
            addAllowedOriginPattern("*")
            addAllowedHeader("*")
            addAllowedMethod("*")
            allowCredentials = true
        }

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}