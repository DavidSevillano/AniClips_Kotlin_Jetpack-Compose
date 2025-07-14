package com.burixer85.aniclips.data.response

import com.burixer85.aniclips.domain.model.auth.UserRegister
import java.util.UUID

data class RegisterResponse(
    val id: UUID,
    val username: String,
    val roles: List<String>,
)

fun RegisterResponse.toDomain(): UserRegister {
    return UserRegister(
        id = id,
        username = username,
        role = roles,
    )
}
