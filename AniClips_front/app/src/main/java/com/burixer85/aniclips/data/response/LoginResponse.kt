package com.burixer85.aniclips.data.response

import java.util.UUID

data class LoginResponse(
    val id: UUID,
    val username: String,
    val avatar: String,
    val roles: List<String>,
    val token: String,
    val refreshToken: String
)
