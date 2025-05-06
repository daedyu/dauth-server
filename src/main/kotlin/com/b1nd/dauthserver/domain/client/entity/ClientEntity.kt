package com.b1nd.dauthserver.domain.client.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "client")
class ClientEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var clientId: UUID? = null,
    var dodamId: String,
    var clientName: String,
    var clientUrl: String,
    var redirectUrl: String
)