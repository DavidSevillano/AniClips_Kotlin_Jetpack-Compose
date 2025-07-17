package com.burixer85.aniclips.data.response.auth

import com.burixer85.aniclips.domain.model.auth.UserLogin
import java.util.UUID

data class LoginResponse(
    val id: UUID,
    val username: String,
    val avatar: String,
    val roles: List<String>,
    val token: String,
    val refreshToken: String
)

fun LoginResponse.toDomain(): UserLogin {
    return UserLogin(
        id = id,
        username = username,
        avatar = avatar,
        role = roles,
        token = token
    )
}
