package com.b1nd.dauthserver.infrastructure.adapter.driven.security.exception

import com.b1nd.dauthserver.infrastructure.adapter.driven.execption.BasicException

class EmptyTokenException: BasicException(401, "Empty token")