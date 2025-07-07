package com.burixer85.aniclips.data.response

import java.util.UUID

data class RegisterResponse(
    val id: UUID,
    val username: String,
    val roles: List<String>,
)
