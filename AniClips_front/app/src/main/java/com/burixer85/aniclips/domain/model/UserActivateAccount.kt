package com.burixer85.aniclips.domain.model

import java.util.UUID

data class UserActivateAccount(
    val id: UUID,
    val username: String,
    val role: List<String>,
)
