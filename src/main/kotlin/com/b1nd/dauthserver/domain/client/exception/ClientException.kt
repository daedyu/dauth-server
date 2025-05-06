package com.b1nd.dauthserver.domain.client.exception

import com.b1nd.dauthserver.infrastructure.adapter.driven.execption.BasicException

class ClientAlreadyException: BasicException(404, "Client already exists");