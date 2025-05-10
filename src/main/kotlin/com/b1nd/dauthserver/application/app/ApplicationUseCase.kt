package com.b1nd.dauthserver.application.app

import com.b1nd.dauthserver.domain.app.service.ApplicationService
import org.springframework.stereotype.Component

@Component
class ApplicationUseCase(
    private val service: ApplicationService
) {

}