package com.b1nd.dauthserver.domain.auth.exception

import com.b1nd.dauthserver.domain.common.exception.BasicException

class AuthenticationWordNotFoundException : BasicException(AuthenticationWordStatusCode.AUTHENTICATION_WORD_NOT_FOUND)