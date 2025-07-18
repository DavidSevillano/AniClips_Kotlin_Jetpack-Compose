package com.burixer85.aniclips.domain.model.auth

import java.util.UUID

data class UserActivateAccount(
    val id: UUID,
    val username: String,
    val role: List<String>,
)
