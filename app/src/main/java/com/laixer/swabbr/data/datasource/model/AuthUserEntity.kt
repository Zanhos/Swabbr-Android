package com.laixer.swabbr.data.datasource.model

import com.auth0.android.jwt.JWT
import com.laixer.swabbr.domain.model.AuthUser
import com.squareup.moshi.Json

data class AuthUserEntity(
    @field:Json(name = "token") val jwtToken: String?,
    @field:Json(name = "user") val user: UserEntity,
    @field:Json(name = "userSettings") val userSettings: SettingsEntity?
)

fun AuthUserEntity.mapToDomain(): AuthUser = AuthUser(jwtToken?.let { JWT(it) }, user.mapToDomain(), userSettings?.mapToDomain())
fun AuthUser.mapToData(): AuthUserEntity = AuthUserEntity(jwtToken?.toString(), user.mapToData(), userSettings?.mapToData())

fun List<AuthUserEntity>.mapToDomain(): List<AuthUser> = map { it.mapToDomain() }
fun List<AuthUser>.mapToData(): List<AuthUserEntity> = map { it.mapToData() }
