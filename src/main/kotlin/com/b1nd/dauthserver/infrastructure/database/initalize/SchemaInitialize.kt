package com.b1nd.dauthserver.infrastructure.database.initalize

import com.b1nd.dauthserver.domain.app.entity.ApplicationEntity
import com.b1nd.dauthserver.domain.framework.entity.FrameworkEntity
import org.jetbrains.exposed.sql.SchemaUtils
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class SchemaInitialize : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        SchemaUtils.create(FrameworkEntity)
        SchemaUtils.create(ApplicationEntity)
        SchemaUtils.create(UserTable)

    }
}