package com.b1nd.dauthserver.domain.user.support

import org.springframework.data.convert.ReadingConverter
import org.springframework.data.convert.WritingConverter
import org.springframework.core.convert.converter.Converter
import com.b1nd.dauthserver.domain.user.enumeration.ScopeType


@WritingConverter
class UserScopeTypeWritingConverter: Converter<List<ScopeType>, String> {
    override fun convert(value: List<ScopeType>): String =
        value.joinToString(" ") { it.value }
}

@ReadingConverter
class UserScopeTypeReadingConverter: Converter<String, List<ScopeType>> {
    override fun convert(value: String): List<ScopeType> =
        value.split(" ").map { ScopeType.fromValue(it) }
}