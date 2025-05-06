package com.b1nd.dauthserver.domain.client.repository

import com.b1nd.dauthserver.domain.client.entity.ClientEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ClientRepository: JpaRepository<ClientEntity, UUID> {
    fun findByClientName(clientName: String): ClientEntity?
}