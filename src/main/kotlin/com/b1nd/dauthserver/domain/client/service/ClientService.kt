package com.b1nd.dauthserver.domain.client.service

import com.b1nd.dauthserver.domain.client.entity.ClientEntity
import com.b1nd.dauthserver.domain.client.repository.ClientRepository
import org.springframework.stereotype.Service

@Service
class ClientService(
    private val repository: ClientRepository
) {
    fun save(client: ClientEntity) {
        repository.save(client)
    }

    fun existByName(name: String): Boolean =
        repository.findByClientName(name) != null
}