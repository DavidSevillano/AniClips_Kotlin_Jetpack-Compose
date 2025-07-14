package com.burixer85.aniclips.domain.model.auth

import java.util.UUID

data class UserRegister(
    val id: UUID,
    val username: String,
    val role: List<String>,
)
