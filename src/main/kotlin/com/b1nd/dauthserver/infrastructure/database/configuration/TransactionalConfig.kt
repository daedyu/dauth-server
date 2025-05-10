package com.b1nd.dauthserver.infrastructure.database.configuration

import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.r2dbc.connection.R2dbcTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
class TransactionalConfig {
    @Bean
    fun transactionManager(connectionFactory: ConnectionFactory): R2dbcTransactionManager =
        R2dbcTransactionManager(connectionFactory)
}