package com.b1nd.dauthserver.domain.user.exception

import com.b1nd.dauthserver.domain.common.exception.BasicException
import com.b1nd.dauthserver.domain.user.enumeration.UserExceptionStatusCode

class UserNotFoundException: BasicException(UserExceptionStatusCode.USER_NOT_FOUND)

class CodeNotFoundException: BasicException(UserExceptionStatusCode.CODE_NOT_FOUND)

class CodeNotAppliedException: BasicException(UserExceptionStatusCode.CODE_NOT_APPLIED)