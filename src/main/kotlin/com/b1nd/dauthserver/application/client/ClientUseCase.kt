package com.b1nd.dauthserver.application.client

import com.b1nd.dauthserver.application.client.data.Client
import com.b1nd.dauthserver.application.client.port.ClientPort
import com.b1nd.dauthserver.domain.client.exception.ClientAlreadyException
import com.b1nd.dauthserver.domain.client.service.ClientService

class ClientUseCase(
    private val service: ClientService,
): ClientPort {
    override fun saveClient(client: Client) {
        if (service.existByName(client.name)) throw ClientAlreadyException()
        service.save(client.toEntity())
    }
}