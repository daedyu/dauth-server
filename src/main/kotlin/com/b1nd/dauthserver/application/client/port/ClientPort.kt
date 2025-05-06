package com.b1nd.dauthserver.application.client.port

import com.b1nd.dauthserver.application.client.data.Client

interface ClientPort {
    fun saveClient(client: Client)
}