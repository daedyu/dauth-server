package com.b1nd.dauthserver.domain.app.repository

import com.b1nd.dauthserver.domain.app.entity.ApplicationEntity
import com.b1nd.dauthserver.domain.app.entity.data.ApplicationWithFrameworks
import com.b1nd.dauthserver.domain.framework.entity.FrameworkEntity
import com.b1nd.dauthserver.domain.framework.enumeration.FrameworkType
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository

@Repository
class ApplicationQueryRepository(
    private val databaseClient: DatabaseClient
) {
    suspend fun findApplicationsByOwnerId(ownerId: String): List<ApplicationWithFrameworks> {
        val sql = """
            SELECT
                a.id AS app_id, a.name AS app_name, a.owner_id, a.client_id, a.client_secret, a.url, a.redirect_url, a.is_public,
                f.id AS fw_id, f.name AS fw_name, f.type AS fw_type, f.color AS fw_color
            FROM applications a
            LEFT JOIN application_frameworks af ON a.id = af.fk_application_id
            LEFT JOIN frameworks f ON af.fk_framework_id = f.id
            WHERE a.owner_id = :ownerId
            ORDER BY a.id
        """.trimIndent()

        val rows = databaseClient.sql(sql)
            .bind("ownerId", ownerId)
            .let(::mapApplicationWithFrameworks)
            .awaitSingle()

        return groupApplications(rows)
    }

    suspend fun findAllApplicationsWithFrameworks(): List<ApplicationWithFrameworks> {
        val sql = """
            SELECT
                a.id AS app_id, a.name AS app_name, a.owner_id, a.client_id, a.client_secret, a.url, a.redirect_url, a.is_public,
                f.id AS fw_id, f.name AS fw_name, f.type AS fw_type, f.color AS fw_color
            FROM applications a
            LEFT JOIN application_frameworks af ON a.id = af.fk_application_id
            LEFT JOIN frameworks f ON af.fk_framework_id = f.id
            WHERE a.is_public = true
            ORDER BY a.id
        """.trimIndent()

        val rows = databaseClient.sql(sql)
            .let(::mapApplicationWithFrameworks)
            .awaitSingle()

        return groupApplications(rows)
    }

    private fun mapApplicationWithFrameworks(spec: DatabaseClient.GenericExecuteSpec) =
        spec.map { row, _ ->
            val app = ApplicationEntity(
                id = row.get("app_id", java.lang.Long::class.java)?.toLong(),
                name = row.get("app_name", String::class.java)!!,
                ownerId = row.get("owner_id", String::class.java)!!,
                clientId = row.get("client_id", String::class.java) ?: "",
                clientSecret = row.get("client_secret", String::class.java) ?: "",
                url = row.get("url", String::class.java) ?: "",
                redirectUrl = row.get("redirect_url", String::class.java) ?: "",
                isPublic = (row.get("is_public", java.lang.Boolean::class.java) ?: false) as Boolean
            )
            val frameworkId = row.get("fw_id", java.lang.Long::class.java)
            val framework = frameworkId?.let {
                FrameworkEntity(
                    id = it.toLong(),
                    name = row.get("fw_name", String::class.java) ?: "",
                    type = row.get("fw_type", String::class.java)?.let { FrameworkType.valueOf(it) }
                        ?: FrameworkType.BACKEND,
                    color = row.get("fw_color", String::class.java) ?: ""
                )
            }
            app to framework
        }.all().collectList()

    private fun groupApplications(rows: List<Pair<ApplicationEntity, FrameworkEntity?>>): List<ApplicationWithFrameworks> {
        return rows.groupBy({ it.first }, { it.second })
            .map { (app, frameworks) ->
                ApplicationWithFrameworks(app, frameworks.filterNotNull())
            }
    }
}
