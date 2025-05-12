package com.b1nd.dauthserver.domain.common.support

import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.data.convert.WritingConverter
import java.nio.ByteBuffer
import java.util.UUID

@WritingConverter
class UUIDWritingConverter: Converter<UUID, ByteArray> {
    override fun convert(source: UUID): ByteArray =
        ByteBuffer.wrap(ByteArray(16)).apply {
            putLong(source.mostSignificantBits)
            putLong(source.leastSignificantBits)
        }.array()
}

@ReadingConverter
class UUIDReadingConverter: Converter<ByteArray, UUID> {
    override fun convert(source: ByteArray): UUID =
        ByteBuffer.wrap(source).run {
            UUID(this.long, this.long)
        }
}