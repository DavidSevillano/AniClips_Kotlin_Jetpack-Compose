package com.burixer85.aniclips.domain.model

import java.util.UUID

data class UserLogin(
    val id: UUID,
    val username: String,
    val avatar: String,
    val role: List<String>,
    val token: String
)