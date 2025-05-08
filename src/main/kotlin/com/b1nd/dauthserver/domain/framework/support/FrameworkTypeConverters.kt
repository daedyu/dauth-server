package com.b1nd.dauthserver.domain.framework.support

import com.b1nd.dauthserver.domain.framework.enumeration.FrameworkType
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.data.convert.WritingConverter

@WritingConverter
class FrameworkTypeWritingConverter: Converter<FrameworkType, String> {
    override fun convert(source: FrameworkType): String = source.name
}

@ReadingConverter
class FrameworkTypeReadingConverter: Converter<String, FrameworkType> {
    override fun convert(source: String): FrameworkType = FrameworkType.valueOf(source)
}