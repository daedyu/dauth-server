package com.b1nd.dauthserver.infrastructure.database.configuration

import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import com.b1nd.dauthserver.domain.framework.support.FrameworkTypeReadingConverter
import com.b1nd.dauthserver.domain.framework.support.FrameworkTypeWritingConverter
import com.b1nd.dauthserver.domain.user.support.UserScopeTypeReadingConverter
import com.b1nd.dauthserver.domain.user.support.UserScopeTypeWritingConverter
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@Configuration
@EnableR2dbcRepositories
class R2dbcConfig(
    private val connectionFactory: ConnectionFactory
): AbstractR2dbcConfiguration() {
    override fun connectionFactory(): ConnectionFactory = connectionFactory

    override fun getCustomConverters() = listOf(
        FrameworkTypeWritingConverter(), FrameworkTypeReadingConverter(),
        UserScopeTypeWritingConverter(), UserScopeTypeReadingConverter()
    )
}