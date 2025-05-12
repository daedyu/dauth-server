package com.b1nd.dauthserver.domain.user.repository

import com.b1nd.dauthserver.domain.user.entity.UserEntity
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.UUID

interface UserRepository: CoroutineCrudRepository<UserEntity, UUID> {
    suspend fun findByDodamId(dodamId: String): UserEntity?
    suspend fun findByDodamIdAndClient(dodamId: String, client: String): UserEntity?

    @Query("""
        INSERT INTO users (id, dodam_id, fk_client_id, scopes, refresh_token, role) 
        VALUES (:#{#user.id}, :#{#user.dodamId}, :#{#user.client}, :#{#user.scopes}, :#{#user.refreshToken}, :#{#user.role});
        SELECT * FROM users WHERE id = :#{#user.id}
    """)
    suspend fun insert(user: UserEntity): UserEntity
}